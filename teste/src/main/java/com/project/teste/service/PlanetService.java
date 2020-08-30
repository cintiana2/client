package com.project.teste.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.teste.entity.Planet;
import com.project.teste.exception.DomainException;
import com.project.teste.repository.PlanetRepository;
import com.project.teste.util.StringUtil;
import com.project.teste.vo.PlanetRequestVO;
import com.project.teste.vo.PlanetVO;

@Service
public class PlanetService{
	
	@Autowired
	private PlanetRepository repository;
	
	@Autowired
	private IntegrationService integrationService;
	
	public Long save(PlanetRequestVO vo) throws DomainException {
		if(StringUtil.isValid(vo.getName())) {
			Planet planet = new Planet(vo);
			try {
				repository.save(planet);
				return planet.getId();
			} catch (Exception s) {
				throw new DomainException(s.getLocalizedMessage());
			} 
		}else {
			throw new DomainException("Nome do planeta é obrigatório");
		}
	}
	
	public PlanetVO getById(Long id) throws DomainException {
		try { 
		  PlanetVO vo = repository.getVoById(id);
		  integrationService.loadNumberMovies(vo);
		  return vo;
		  
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
	
	public List<PlanetVO> findByName(String name) throws DomainException {
		List<PlanetVO> list =repository.findByPartName(name);
		integrationService.loadNumberMovies(list);
		return list;
	}
	
	public List<PlanetVO> listAll() throws DomainException {
		List<PlanetVO> list = repository.listAllVo();;
		integrationService.loadNumberMovies(list);
		return list;
	}
	
	public void delete(Long id) throws DomainException {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new DomainException(e.getLocalizedMessage());
		}
	}
	
}
