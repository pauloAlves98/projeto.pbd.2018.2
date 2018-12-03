package br.com.palves.pbd.enums;

public enum StatusEnum {
	ATIVO("ATIVO");
	
	private String v;
	private StatusEnum(String h) {
		v=h;
	}
	public String getValor() {
		return v;
	}
}
