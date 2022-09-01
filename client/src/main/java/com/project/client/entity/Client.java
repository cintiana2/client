package com.project.client.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;

import com.project.client.util.enums.StatusClientEnum;
import com.project.client.vo.ClientVO;

@Entity
public class Client {

    @Id
    @Column(name = "CLI_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQ")
    @SequenceGenerator(sequenceName = "CLIENT_SEQ", initialValue = 1, allocationSize = 1, name = "CLIENT_SEQ")
    private Long id;
    
    @Length(max = 200) 
    @Column(name = "CLI_NAME", nullable = false, length = 200)
    private String name;
    
    @Length(max = 200) 
    @Column(name = "CLI_EMAIL", nullable = true, length = 200)
    private String email;
    
    @Column(name = "CLI_BIRTHDAY", nullable = false)
    private LocalDate birthday;
    
    @Column(name = "CLI_STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusClientEnum status = StatusClientEnum.ENABLE;

	@OneToOne(fetch=FetchType.EAGER, optional = true, cascade = {CascadeType.ALL})
	@JoinColumn(name="CLI_ADDRESS_ID", nullable=true)
	private Address adress;
	
	@Transient
	private Integer age;
   
    public Client() {
		
	}
    
    public Client(ClientVO vo) {
    	this.id = vo.getId();
		this.name = vo.getName();
		this.email = vo.getEmail();
		this.birthday = vo.getBirthday();
		this.status = StatusClientEnum.ENABLE;
		if(vo.getAdress() !=null) {
			this.adress = new Address(vo.getAdress());
		}
		
   	}

	public Client(Long id, @Length(max = 200) String name, @Length(max = 200) String email, LocalDate birthday,
			StatusClientEnum status, Address adress) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthday = birthday;
		this.status = status;
		this.adress = adress;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public StatusClientEnum getStatus() {
		return status;
	}

	public void setStatus(StatusClientEnum status) {
		this.status = status;
	}

	public Address getAdress() {
		return adress;
	}

	public void setAdress(Address adress) {
		this.adress = adress;
	}

	public Integer getAge() {
		if( this.getBirthday() !=null) {
			return Period.between(this.getBirthday(), LocalDate.now()).getYears();
		}
		
		return age;
	}
    
}