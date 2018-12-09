package br.com.palves.pbd.enums;

public enum Discriminador {
	PF("PF"),PJ("PJ"),CP("CP"),CN("CN"),CG("CG");
	
	private String valor;
	
   private  Discriminador(String v) {
		this.valor = v;
	}
	public String getValor() {
		return valor;
	}
}
