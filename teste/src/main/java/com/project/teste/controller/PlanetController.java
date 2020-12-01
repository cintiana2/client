package com.project.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.teste.exception.DomainException;
import com.project.teste.service.PlanetService;
import com.project.teste.vo.PlanetRequestVO;

@RestController
@RequestMapping(value = "/api/v1/planet")
public class PlanetController
{
	
	@Autowired
	private PlanetService planetService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody PlanetRequestVO planet) {
		try {
			Long id = planetService.save(planet);
			return ResponseEntity.ok(id);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updated(@RequestBody PlanetRequestVO planet) {
		try {
			Long id = planetService.save(planet);
			return ResponseEntity.ok(id);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			planetService.delete(id);
			return ResponseEntity.ok(true);
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		
		try {
			return ResponseEntity.ok(planetService.getById(id));
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		try {
			return ResponseEntity.ok(planetService.listAll());
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	@GetMapping("find-by-name")
	public ResponseEntity<?> findByName(@RequestParam("name") String name) {
		try {
			return ResponseEntity.ok(planetService.findByName(name));
		} catch (DomainException e) {
			return catchExceptionDomain(e);
		}
	}
	
	private ResponseEntity<?> catchExceptionDomain(DomainException exception ){
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
