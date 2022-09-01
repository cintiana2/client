package com.project.client.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.client.response.PlanetsSwapiResponse;

public class IntegrationApiUtil {
	
	private static Logger logger = LogManager.getLogger(IntegrationApiUtil.class);

	public static boolean isUrlValid(String url) {

		try {
			new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static HttpEntity<?> createHeader() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Vary", "Accept");
		headers.add("Content-Type", "application/json");
		headers.add("Allow", "GET");
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);

		return httpEntity;
	}

	public static ResponseEntity<PlanetsSwapiResponse> callUrlPlanet(String url) {
		ResponseEntity<PlanetsSwapiResponse> response;
		try {
			response = realizeCallPlanet(url);
		} catch (RestClientException e) { // a api está retornando 301 para url que ela mesmo informa
			url = url.replace("http", "https");
			response = realizeCallPlanet(url);
		}
		logger.info("Chamado OK");
		return response;
	}
	
	private static ResponseEntity<PlanetsSwapiResponse> realizeCallPlanet(String url){
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Chamando url: " + url);
		return restTemplate.exchange(url, HttpMethod.GET, createHeader(), PlanetsSwapiResponse.class);
	}

}
