package com.project.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.client.exception.DomainException;
import com.project.client.service.AdressIntegrationService;

/**
 * Classe para ajudar preencher endereços, pode ser usada para chamadas
 * paralelas, mas seu uso não é obrigatório
 * @author cintia
 *
 */
@RestController
@RequestMapping(value = "/api/v1/util/adress")
public class AddressIntegrationController
{
	@Autowired
	private AdressIntegrationService service;
	
	@GetMapping("/ufs")
	public ResponseEntity<?> listUfs() {
		
		try {
			return ResponseEntity.ok(service.listFus());
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("/{idUF}/cities")
	public ResponseEntity<?> findCities(@PathVariable Long idUF) {
		
		try {
			return ResponseEntity.ok(service.listCityByFU(idUF));
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("/{cep}/address")
	public ResponseEntity<?> findCities(@PathVariable String cep) {
		
		try {
			return ResponseEntity.ok(service.findAddresByZipcode(cep));
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	private ResponseEntity<?> catchExceptionDomain(DomainException exception ){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
