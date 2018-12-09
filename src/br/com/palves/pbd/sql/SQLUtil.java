package br.com.palves.pbd.sql;

public class SQLUtil {//CONSULTAS EM SOMENTE CREATEQUERY E NATIVE;
	public  static class Pessoa {
		public static  String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";

	}
	public static class PessoaFisica {
		//public static String NATIVEQUERY_BUSCAR_UNIQUE_CPF = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
		public static  String NATIVEQUERYPROCEDURE_VALIDA_HABILITACAO = "Select validahabilitacao(:nHab)";
		public static String NATIVEQUERY_CREATEPROCEDURE_VALIDA_HABILITACAO = "CREATE OR REPLACE FUNCTION validahabilitacao(nHab varChar) "
				+ "returns boolean AS $BODY$ "
				+ "BEGIN "
				+ "IF EXISTS (select p.n_habilitacao from pessoa_fisica p where p.n_habilitacao = nHab) THEN " + 
				"		return true;" + 
				"	ELSE" + 
				"		return false; " + 
				"	END IF; " + 
				"END;$BODY$" + 
				"  LANGUAGE plpgsql VOLATILE " + 
				"  COST 100;";
	}
	public static class Funcionario{
		public static String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.nome, p.id From Funcionario p Where p.login ILIKE ? and p.senha = ?";

		//Gatilho
		/*
		CREATE TRIGGER gatilhoFunc
		AFTER INSERT OR UPDATE OR DELETE ON funcionario
		    FOR EACH ROW EXECUTE PROCEDURE gatilhoLog();
		 */

		//GatilhoProcdure
		/*CREATE OR REPLACE FUNCTION gatilholog()
  RETURNS trigger AS
$BODY$DECLARE 
	dados varChar:= 'A';
BEGIN
	IF (TG_OP = 'DELETE') THEN
	    dados = OLD.nome ||';'|| OLD.cpf;
            INSERT INTO Log (id,usuario,registro_antigo,tipo_alteracao,dataalteracao,tabela) values(nextVal('seq_log_id'),user,dados,'DELETE',now(),TG_RELNAME);
        -- Registrar quem alterou a linha e quando
        ELSIF (TG_OP = 'UPDATE') THEN
          dados = OLD.nome || ';' || OLD.cpf;
            INSERT INTO Log (id,usuario,registro_antigo,tipo_alteracao,dataalteracao,tabela) values(nextVal('seq_log_id'),user,dados,'DELETE',now(),TG_RELNAME);
        END IF;
	RETURN OLD;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;*/
	}
	public static class Filial{
		public static String NATIVEQUERY_BUSCAR_ID_POR_NOME = "Select p.nome, p.id From Filial p Where p.nome = ?";
	}
	public static class  Categoria{
		public static String NATIVEQUERY_BUSCAR_ID_DISC_POR_NOME = "SELECT p.id, p.discriminador FROM Categoria p WHERE p.nome = ?";
	}
}
