package com.project.client.vo;

public class PlanetRequestVO {
	
	 private Long id; 

	 private String name;
	 
	 private String climate;
	 
	 private String ground;
	
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}
}
