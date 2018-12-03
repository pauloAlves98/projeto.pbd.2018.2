package br.com.palves.pbd.sql;

public class SQLUtil {//CONSULTAS EM SOMENTE CREATEQUERY E NATIVE;
	public  static class Pessoa {
		public static  String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
		
	}
	public static class PessoaFisica {
		//public static String NATIVEQUERY_BUSCAR_UNIQUE_CPF = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
		//public static String NATIVEQUERY_BUSCAR_UNIQUE_LOGIN = 
	}
	public static class Funcionario{
		public static String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.nome, p.id From Funcionario p Where p.login ILIKE ? and p.senha = ?";
	}
	public static class Filial{
		public static String NATIVEQUERY_BUSCAR_ID_POR_NOME = "Select p.nome, p.id From Filial p Where p.nome = ?";
	}
}
