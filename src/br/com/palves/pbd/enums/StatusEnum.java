package br.com.palves.pbd.enums;

public enum StatusEnum {
	ATIVO("ATIVO"),DESATIVADO("DESATIVADO"),EM_ESPERA("EM ESPERA"),CANCELADA("CANCELADA"),EFETUADA("EFETUADA");
	
	private String v;
	private StatusEnum(String h) {
		v=h;
	}
	public String getValor() {
		return v;
	}
}
