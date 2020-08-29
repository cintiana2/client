package com.project.teste;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.teste.exception.DomainException;
import com.project.teste.response.PlanetResponse;
import com.project.teste.service.IntegrationService;
import com.project.teste.service.PlanetService;
import com.project.teste.vo.PlanetRequestVO;
import com.project.teste.vo.PlanetVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TesteApplication.class})

class TesteApplicationTests {
	
	@Autowired
	private PlanetService planetService;
	
	@Autowired
	private IntegrationService integrationService;
 
	@Test
	public void testCreateAndRemove() throws DomainException {
	  PlanetVO planet = savePlanet(null);
	  assertNotNull(planet.getId(), "Erro ao criar");
	  long id = planet.getId();
	  removePlanet(id);
	  PlanetVO searchPlanet = planetService.getById(id);
	  assertNull("Erro ao excluir ", searchPlanet);
	}
	
	@Test
	public void testGetById() throws DomainException {
		PlanetResponse pr = integrationService.getPlanetWithFilms();
		PlanetVO planet;
		if(pr == null) {
			planet = savePlanet(null);
		}else {
			planet = savePlanet(pr.getName());
		}
		PlanetVO searchPlanet = planetService.getById(planet.getId());
		assertNotNull(searchPlanet, "Erro ao buscar por id" );
		if(pr !=null) {
			assertTrue(searchPlanet.getNumberMovies() >0, "Erro ao preencher número de filmes");
		}
		
		removePlanet(planet.getId());
	}
	
	@Test
	public void testListAll() throws DomainException {
		PlanetVO planet = savePlanet(null);
		List<PlanetVO> planets = planetService.listAll();
		assertTrue(planets !=null && planets.size() >0, "Erro listAll: retornou lista vazia");
		assertTrue(existPlanet(planets, planet.getId()), "Erro listAll: Não retornou planeta criado");
		removePlanet(planet.getId());
	}
	
	private boolean existPlanet (List<PlanetVO> planets, Long id) {
		for(PlanetVO planet : planets) {
			if(planet.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	@Test
	public void testFindByName() throws DomainException {
		PlanetVO planet = savePlanet(null);
		List<PlanetVO> planets = planetService.findByName(planet.getName());
		assertTrue(planets !=null && planets.size() >0, "Erro FindBynName: retornou vazio");
		assertTrue(existPlanetName(planets, planet.getName()), "Erro listAll: Não retornou planeta criado");
		removePlanet(planet.getId());
	}
	
	private boolean existPlanetName (List<PlanetVO> planets, String name) {
		for(PlanetVO planet : planets) {
			if(planet.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 *  Métodos para carregar planeta e limpar
	 */
	/**
	 * Se o nome for null, um outro nome é colocado
	 * @param name
	 * @return
	 * @throws DomainException
	 */
	private PlanetVO savePlanet(String name) throws DomainException {
		PlanetRequestVO planetRequest = createPlanet(name);
		Long id = planetService.save(planetRequest);
		PlanetVO planetWithId = new PlanetVO();
		planetWithId.setClimate(planetRequest.getClimate());
		planetWithId.setGround(planetRequest.getGround());
		planetWithId.setId(id);
		planetWithId.setName(planetWithId.getName());
		return planetWithId;
	}
	
	private PlanetRequestVO createPlanet(String name) {
		PlanetRequestVO planet = new PlanetRequestVO();
		if(name == null || name.isBlank()) {
			planet.setName("Eriadu");
		}else {
			planet.setName(name);
		}
		planet.setClimate("climate");
		planet.setGround("ground"); 
		return planet;
	}
	
	private void removePlanet(Long id) throws DomainException {
		planetService.delete(id);
	}

}
