package com.project.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.client.exception.DomainException;
import com.project.client.response.PlanetResponse;
import com.project.client.response.PlanetsSwapiResponse;
import com.project.client.util.IntegrationApiUtil;
import com.project.client.util.PropertiesUtil;
import com.project.client.util.StringUtil;
import com.project.client.vo.PlanetVO;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Service
public class IntegrationService {
	
	private static Logger logger = LogManager.getLogger(IntegrationService.class);
	
	private static final String URL_PLANET = PropertiesUtil.getUrlValue("url.integration.planet");
	 
	 public void loadNumberMovies(PlanetVO vo) throws DomainException{
		 if(vo !=null && StringUtil.isValid(vo.getName()) ) {
			  PlanetResponse planet = findPlanetSW(vo.getName());
			  loadNumberMoviesInPlanet(vo, planet);
		 }
	  }
	 
	 public void loadNumberMovies(List<PlanetVO> list) throws DomainException {
			try {
				if (list != null) {
					List<PlanetResponse> planets = findPlanetSwapApi();
					if (planets != null && planets.size() > 0) {
						for (PlanetVO vo : list) {
							PlanetResponse planet = getPlanetByName(planets, vo.getName());
							loadNumberMoviesInPlanet(vo, planet);
						}
					}
				}
			} catch (Exception e) {
				logger.error("Erro ao carregar número de filmes em planeta", e);
				throw new DomainException("Erro ao acessar api para buscar número de filmes. Tente novamente");
			}
		}
	 
	 public PlanetResponse findPlanetSW(String namePlanet) {
		 List<PlanetResponse> allPlanets = new ArrayList<PlanetResponse>();
         String nextUrl = URL_PLANET;
		 while (nextUrl !=null && IntegrationApiUtil.isUrlValid(nextUrl)) {
			 nextUrl = addPlanetsInlist(allPlanets, IntegrationApiUtil.callUrlPlanet(nextUrl));
			 PlanetResponse planet = getPlanetByName(allPlanets, namePlanet);
			 allPlanets = new ArrayList<PlanetResponse>();
			 if(planet !=null) {
				 return planet;
			 }
	     }
		 return null;
	 }
	 
	 private PlanetResponse getPlanetByName( List<PlanetResponse> planets, String namePlanet) {
		 for(PlanetResponse planet : planets) {
			 if(planet.getName().equalsIgnoreCase(namePlanet)) {
				 return planet;
			 }
		 }
		 return null;
	 }
	 
	 public PlanetResponse getPlanetWithFilms() {
		 List<PlanetResponse> allPlanets = new ArrayList<PlanetResponse>();
         String nextUrl = URL_PLANET;
		 while (nextUrl !=null && IntegrationApiUtil.isUrlValid(nextUrl)) {
			 nextUrl = addPlanetsInlist(allPlanets, IntegrationApiUtil.callUrlPlanet(nextUrl));
			 PlanetResponse planet = getOnePlanetWithFilmes(allPlanets);
			 allPlanets = new ArrayList<PlanetResponse>();
			 if(planet !=null) {
				 return planet;
			 }
	     }
		 return null;
	 }
	 
	 private PlanetResponse getOnePlanetWithFilmes( List<PlanetResponse> planets) {
		 for(PlanetResponse planet : planets) {
			 if(planet.getFilms() != null && planet.getFilms().size() >0) {
				 return planet;
			 }
		 }
		 return null;
	 }
	 
	 private void loadNumberMoviesInPlanet(PlanetVO vo,PlanetResponse planet) {
			if (planet != null && planet.getFilms() != null) {
				vo.setNumberMovies(planet.getFilms().size());
			}
		} 
	 
	 public List<PlanetResponse> findPlanetSwapApi() {
         List<PlanetResponse> allPlanets = new ArrayList<PlanetResponse>();
         String nextUrl = URL_PLANET;
		 while (nextUrl !=null && IntegrationApiUtil.isUrlValid(nextUrl)) {
			 nextUrl = addPlanetsInlist(allPlanets, IntegrationApiUtil.callUrlPlanet(nextUrl));
			
	     }
		 return allPlanets;
    }
	 
	 private String addPlanetsInlist(List<PlanetResponse> planetsSwapi, ResponseEntity<PlanetsSwapiResponse> response) {
		 if(response !=null && response.getBody().getResults() !=null) {
			 planetsSwapi.addAll(response.getBody().getResults());
			 return response.getBody().getNext();
		 }
		 return null;
	 }
}
