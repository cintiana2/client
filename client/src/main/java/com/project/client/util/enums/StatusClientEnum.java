package com.project.client.util.enums;

public enum StatusClientEnum {

	ENABLE(1,"Ativo"),
	DISABLE(2,"Inativo");
	
	private Integer codigo;
	private String nome;
	
	private StatusClientEnum(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
