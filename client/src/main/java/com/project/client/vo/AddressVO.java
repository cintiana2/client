package com.project.client.vo;

import com.project.client.entity.Address;

public class AddressVO {
	
	private Long id;
	   
	private String city;
	    
	private String fu;
	  
	private Integer number;
	  
	private String complement;
	    
	private String country;
	    
	private String zipcode;
	
	private String district;
	 
	 
	public AddressVO() {
		
	}
	
	public AddressVO(Address address) {
		this.city = address.getCity();
		this.complement = address.getComplement();
		this.fu = address.getFu();
		this.country = address.getCountry();
		this.id = address.getId();
		this.number = address.getNumber();
		this.zipcode = address.getZipcode();
		this.district = address.getDistrict();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String estate) {
		this.fu = estate;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	  
}
