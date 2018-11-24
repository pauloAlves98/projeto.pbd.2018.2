package br.com.palves.pbd.enums;
/**
 * @author: P Alves
 * */
public enum TransicaoTelaEnum {
	LOGIN("Login");
	private String valor;
	
	private TransicaoTelaEnum(String v) {
		this.valor = v;
	}
	public String getValor() {
		return valor;
	}
	
}
