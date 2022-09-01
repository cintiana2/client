package com.project.client;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.client.exception.DomainException;
import com.project.client.service.ClientService;
import com.project.client.vo.AddressVO;
import com.project.client.vo.ClientVO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ClientApplication.class})

class ClientServiceTests {
	
	
	@Autowired
	private ClientService clientService;
 
	@Test
	public void testCreateAndRemoveClientWithRequiredElements() throws DomainException {
	  ClientVO client = saveClient(null, null,false);
	  assertNotNull(client.getId(), "Erro ao criar");
	  long id = client.getId();
	  removeClient(id);
	  ClientVO searchClient = clientService.getById(id);
	  assertNull("Erro ao excluir ", searchClient);
	 }
	
	
	@Test
	public void testGetById() throws DomainException {
		
		LocalDate date = LocalDate.of(1984, Month.APRIL, 1);
		ClientVO client = saveClient("Maria", date, false);
		
		ClientVO searchClient =clientService.getById(client.getId());
		assertNotNull(searchClient, "Erro ao buscar por id" );	
		assertNotNull(searchClient.getAge(), "Erro ao calcular idade" );	
		
		removeClient(client.getId());
	}
	/*
	@Test
	public void testGetById() throws DomainException {
		
		LocalDate date = LocalDate.of(1984, Month.APRIL, 1);
		ClientVO client = saveClient("Maria", date, false);
		
		PlanetVO searchPlanet = planetService.getById(planet.getId());
		assertNotNull(searchPlanet.getage, "Erro ao buscar por id" );
		
		
		
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
		PlanetVO planet = savePlanet("Planeta client");
		List<PlanetVO> planets = planetService.findByName(planet.getName());
		assertTrue(planets !=null && planets.size() >0, "Erro FindByName: retornou vazio");
		assertTrue(existPlanetName(planets, planet.getName()), "Erro FindByName: Não retornou planeta criado");
		removePlanet(planet.getId());
	}
	
	private boolean existPlanetName (List<PlanetVO> planets, String name) {
		for(PlanetVO planet : planets) {
			if(planet.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}*/
	
	/*
	 *  Métodos para carregar planeta e limpar
	 */
	
	/**
	 * Se o nome for null, um outro nome é colocado
	 * @param name
	 * @return
	 * @throws DomainException
	 */
	private ClientVO saveClient(String name, LocalDate date, boolean withAddress) throws DomainException {
		ClientVO clientVo = withAddress ? createClientwithAdress(name, date) :createClient(name, date);
		
		Long id = clientService.save(clientVo);
		clientVo.setId(id);
		
		return clientVo;
	}
	
	private ClientVO createClientwithAdress(String name, LocalDate date) {
		ClientVO vo = createClient(name, date);
		vo.setAdress(createAddress());
		return vo;
	}
	
	private ClientVO createClient(String name, LocalDate date) {
		ClientVO vo = new ClientVO();
		if(name == null || name.isBlank()) {
			vo.setName("Pessoa da Silva");
		}else {
			vo.setName(name);
		}
		
		if(date == null) {
			date = LocalDate.now();
		}
		
		vo.setBirthday(date);
		return vo;
	}
	
	private AddressVO createAddress() {
		AddressVO addressVo = new AddressVO();
		addressVo.setCity("City");
		addressVo.setComplement("complement");
		addressVo.setDistrict("District");
		addressVo.setFu("UF");
		addressVo.setNumber(10);
		addressVo.setZipcode("11111111");
		return addressVo;
	}
	
	private void removeClient(Long id) throws DomainException {
		clientService.delete(id);
	}
	
}
