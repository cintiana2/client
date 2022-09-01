package com.project.client.vo;

import java.time.LocalDate;

public class ClientRequestVO {
	
	 	private Long id;
	    
	   
	    private String name;
	    
	  
	    private String email;
	    
	   
	    private LocalDate birthday;
	
		
		private AddressVO adress;


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


		public AddressVO getAdress() {
			return adress;
		}


		public void setAdress(AddressVO adress) {
			this.adress = adress;
		}
	 
}
