package br.com.palves.pbd.enums;
/**
 * @author: P Alves
 * */
public enum TransicaoTelaEnum {
	LOGIN("Login"),EDITAR_CLIENTEFISICO("Editar Cliente Fisico"),EDITAR_CLIENTEJURIDICO("Editar Cliente Juridico"),CADASTRO_PESSOAFISICA("Cadastro Pessoa Fisica"),CADASTRO_PESSOAJURIDICA("Cadastro Pessoa Juridica"),MENU_CLIENTE("Menu Cliente"),ALTERAR_SENHA("Alterar Senha");
	private String valor;
	
	private TransicaoTelaEnum(String v) {
		this.valor = v;
	}
	public String getValor() {
		return valor;
	}
	
}
