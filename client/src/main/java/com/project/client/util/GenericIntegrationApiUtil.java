package com.project.client.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class GenericIntegrationApiUtil<T> {
	
	private static Logger logger = LogManager.getLogger(GenericIntegrationApiUtil.class);

	
	
	public static boolean isUrlValid(String url) {

		try {
			new URL(url);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	public static HttpEntity<?> createHeaderGet() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Vary", "Accept");
		headers.add("Content-Type", "application/json");
		headers.add("Allow", "GET");
		headers.add("Access-Control-Allow-Origin", "*");
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);

		return httpEntity;
	}

	@SuppressWarnings("unchecked")
	public T realizeCallOneObject(String url, Class classResponse){
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Chamando url: " + url);
		return (T) restTemplate.exchange(url, HttpMethod.GET, createHeaderGet(), classResponse).getBody();

	}
	
	@SuppressWarnings("unchecked")
	public List<T> realizeCallList(String url){
		
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Chamando url: " + url);
		return (List<T>)restTemplate.exchange(url, HttpMethod.GET, createHeaderGet(), List.class).getBody();

	}

}
