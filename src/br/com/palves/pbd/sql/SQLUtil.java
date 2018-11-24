package br.com.palves.pbd.sql;

public class SQLUtil {//CONSULTAS EM SOMENTE CREATEQUERY E NATIVE;
	public  static class Pessoa {
		public static  String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
	}
}
