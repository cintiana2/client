package com.project.teste.vo;

import com.project.teste.entity.Planet;

public class PlanetVO {
	
	 private Long id;
	 private String name;
	 private String climate;
	 private String ground;
	 private Integer numberMovies= 0 ;
	 
	public PlanetVO() {
		
	}
	
	public PlanetVO(Long id, String name, String climate, String ground) {
		super();
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.ground = ground;
	}

	public PlanetVO(Planet planet) {
		this.climate = planet.getClimate();
		this.ground = planet.getGround();
		this.id = planet.getId();
		this.name = planet.getName();
	}
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getGround() {
		return ground;
	}
	public void setGround(String ground) {
		this.ground = ground;
	}

	public Integer getNumberMovies() {
		return numberMovies;
	}

	public void setNumberMovies(Integer numberMovies) {
		this.numberMovies = numberMovies;
	}

}
