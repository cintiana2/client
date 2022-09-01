package com.project.client.vo.integration;

public class FUIbgeVO {
	
	private Long id;
	private String sigla;
	private String nome;
	private RegionIbgeVO regiao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public RegionIbgeVO getRegiao() {
		return regiao;
	}
	
	public void setRegiao(RegionIbgeVO regiao) {
		this.regiao = regiao;
	}

}
