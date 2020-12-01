package com.project.teste.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Length;

import com.project.teste.vo.PlanetRequestVO;
import com.project.teste.vo.PlanetVO;

@Entity
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLANET_SEQ")
    @SequenceGenerator(sequenceName = "planet_seq", initialValue = 1, allocationSize = 1, name = "PLANET_SEQ")
    private Long id;
    

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;
    
    @Length(max = 500) 
    @Column(name = "CLIMATE", nullable = true, length = 500)
    private String climate;
    
    @Length(max = 500)  
    @Column(name = "GROUND", nullable = true, length = 500 )
    private String ground;
    
    
    public Planet() {
		
	}
    
    public Planet(Long id, String name, String climate, String ground) {
  		this.id = id;
  		this.name = name;
  		this.climate = climate;
  		this.ground = ground;
  	}

  	public Planet(PlanetVO planet) {
  		this.id = planet.getId();
  		this.ground = planet.getGround();
  		this.climate = planet.getClimate();
  		this.name = planet.getName();
  	}
  	
  	public Planet(PlanetRequestVO planet) {
  		this.ground = planet.getGround();
  		this.climate = planet.getClimate();
  		this.name = planet.getName();
  		this.id = planet.getId();
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

}
