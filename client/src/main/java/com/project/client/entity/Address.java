package com.project.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.Length;

import com.project.client.vo.AddressVO;

@Entity
public class Address {
	
	public static String DEFAULT_COUNTRY = "Brasil";

    @Id
    @Column(name = "ADR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ")
    @SequenceGenerator(sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1, name = "ADDRESS_SEQ")
    private Long id;
    
    @Length(max = 200) 
    @Column(name = "ADR_CITY", nullable = true, length = 200)
    private String city;
   
    @Length(max = 2) 
    @Column(name = "ADR_FU", nullable = true, length = 2)
    private String fu;
  
    @Column(name = "ADR_NUMBER", nullable = true)
    private Integer number;
    
    @Length(max = 200) 
    @Column(name = "ADR_COMPLEMENT", nullable = true, length = 200)
    private String complement;
    
    @Length(max = 100) 
    @Column(name = "ADR_DISTRICT", nullable = true, length = 100)
    private String district ;
    
    @Length(max = 100) 
    @Column(name = "ADR_COUNTRY", nullable = false, length = 100)
    private String country = DEFAULT_COUNTRY;
    
    @Length(max = 8) 
    @Column(name = "ADR_ZIPCODE", nullable = true, length = 8)
    private String zipcode;
   
    
    public Address() {
		
    }
    
	public Address(AddressVO vo) {
		this.id = vo.getId();
		this.city = vo.getCity();
		this.fu = vo.getFu();
		this.number = vo.getNumber();
		this.complement = vo.getComplement();
		this.country = vo.getCountry();
		this.zipcode = vo.getZipcode();
		this.district = vo.getDistrict();
	 }


	public Address(Long id, @Length(max = 200) String city, @Length(max = 2) String fu, Integer number,
			@Length(max = 200) String complement, @Length(max = 100) String district, @Length(max = 100) String country,
			@Length(max = 8) String zipcode) {
		super();
		this.id = id;
		this.city = city;
		this.fu = fu;
		this.number = number;
		this.complement = complement;
		this.district = district;
		this.country = country;
		this.zipcode = zipcode;
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


	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getFu() {
		return fu;
	}

	public void setFU(String FU) {
		this.fu = FU;
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
	
	
    
}