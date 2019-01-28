package br.com.palves.pbd.sql;

public class SQLUtil {//CONSULTAS EM SOMENTE CREATEQUERY E NATIVE;
	public  static class Pessoa {
		public static  String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
		
		/**CREATE OR REPLACE FUNCTION auditoriaPessoa()
		RETURNS trigger AS
		$BODY$
		DECLARE 
			nomeuser varChar:= 'NULL';
		BEGIN
			IF (TG_OP = 'INSERT') THEN
				iNSERT INTO AuditoriaPessoa (cod,dataalteracao,usuario,tipo_alteracao,tabela,id ,discriminador,login ,nome,senha,situacao,endereco_id,ultimo_modificador) 
		            values(nextVal('seq_AuditoriaPessoa_id'),now(),NEW.ultimo_modificador,'INSERT',TG_RELNAME,NEW.id ,NEW.discriminador,NEW.login,NEW.nome,NEW.senha,NEW.situacao,NEW.endereco_id,NEW.ultimo_modificador);
				RETURN NEW;
		        -- Registrar quem alterou a linha e quando
		        ELSIF (TG_OP = 'UPDATE') THEN
		            INSERT INTO AuditoriaPessoa (cod,dataalteracao,usuario,tipo_alteracao,tabela,id ,discriminador,login ,nome,senha,situacao,endereco_id,ultimo_modificador) 
		            values(nextVal('seq_AuditoriaPessoa_id'),now(),NEW.ultimo_modificador,'UPDATE',TG_RELNAME,OLD.id ,OLD.discriminador,OLD.login,OLD.nome,OLD.senha,OLD.situacao,OLD.endereco_id,NEW.ultimo_modificador);
		            RETURN OLD;
		        END IF;
		END; $BODY$
		LANGUAGE plpgsql VOLATILE
		COST 100;
		CREATE TRIGGER gatilhoAuditoriaPessoa
				AFTER INSERT OR UPDATE OR DELETE ON Pessoa
				    FOR EACH ROW EXECUTE PROCEDURE auditoriaPessoa();**/

	}
	public static class PessoaJurica{
		//Porcedures e Gatiilhos!
		public static  String NATIVEQUERYPROCEDURE_VALIDA_POR_CNPJ = "Select validaPorCNPJ(:icnpj)";
		public static String NATIVEQUERY_CREATEPROCEDURE_VALIDA_POR_CNPJ = "CREATE OR REPLACE FUNCTION validaPorCNPJ(icnpj varChar) "
				+ "returns boolean AS $BODY$ "
				+ "BEGIN "
				+ "IF EXISTS (select p.cnpj from pessoa_juridica p where p.cnpj = icnpj) THEN " + 
				"		return true;" + 
				"	ELSE" + 
				"		return false; " + 
				"	END IF; " + 
				"END;$BODY$" + 
				"  LANGUAGE plpgsql VOLATILE " + 
				"  COST 100;";
	}
	public static class Reserva{
		public static String NATIVEQUERY_CREATE_VIEW_RESERVA = "create view ViewReserva AS select r.data_hora_reserva as DataReserva, r.situacao,cliente.id as codCliente, cliente.nome as cliente, categoria.nome as categoria, filial.nome as filial from Reserva r"+ 
				" inner join Pessoa cliente on cliente.id = r.pessoa_id"+
				" inner join Categoria categoria on categoria.id = r.categoria_id"+
				" inner join Filial filial  on filial.id = r.filial_retirada_id order by r.data_hora_reserva";
		public static String NATIVEQUERY_BUSCARVIEWRESERVA_POR_PERIODO = "SELECT dataReserva,situacao,codCliente,cliente,categoria,filial FROM ViewReserva WHERE dataReserva  >=:var1 and dataReserva <=:var2";
	}
	public static class PessoaFisica {
		//public static String NATIVEQUERY_BUSCAR_UNIQUE_CPF = "Select p.discriminador, p.id From Pessoa p Where p.login ILIKE ? and p.senha = ?";
		public static String NATIVEQUERY_BUSCAR_ID_POR_NOME = "Select p.nome, p.id From Pessoa p Where p.nome = ? and p.discriminador = \'PF\'";
		//public static String NATIVEQUERY_VALIDA_POR_CPF = "Select p.nome, p.id From Pessoa p Where p.nome = ? and p.discriminador = \'PF\'";
		//Procedures e Gatilhos
		//Chamadas
		public static  String NATIVEQUERYPROCEDURE_VALIDA_HABILITACAO = "Select validahabilitacao(:nHab)";
		public static  String NATIVEQUERYPROCEDURE_VALIDA_POR_CPF= "Select validaPorCPF(:icpf)";
		//Criações
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
		public static String NATIVEQUERY_CREATEPROCEDURE_VALIDA_POR_CPF = "CREATE OR REPLACE FUNCTION validaPorCPF(icpf varChar) "
				+ "returns boolean AS $BODY$ "
				+ "BEGIN "
				+ "IF EXISTS (select p.cpf from pessoa_fisica p where p.cpf = icpf) THEN " + 
				"		return true;" + 
				"	ELSE" + 
				"		return false; " + 
				"	END IF; " + 
				"END;$BODY$" + 
				"  LANGUAGE plpgsql VOLATILE " + 
				"  COST 100;";
	}
	public static class Funcionario{
		public static String NATIVEQUERY_BUSCAR_ID_POR_LOGIN = "Select p.nome, p.id, p.cargo From Funcionario p Where p.login ILIKE ? and p.senha = ?";
		
//		CREATE OR REPLACE FUNCTION auditoriaLocacao()
//		RETURNS trigger AS
//		$BODY$
//		DECLARE 
//			nomeuser varChar:= 'NULL';
//		BEGIN
//			IF (TG_OP = 'INSERT') THEN
//				INSERT INTO AuditoriaLocacao (cod,dataAlteracao,usuario,tipo_alteracao,tabela,id,data_retirada,data_entrega,data_real_entrega,
//				km_livre,km_atual,km_retorno,valor_diaria,taxa_higiene,taxa_devolucao,preco_final,situacao,veiculo_id,filial_locataria_id,
//				filial_entrega_id,pessoa_id,motorista_id,funcionario_id) values (nextVal('seq_AuditoriaLocacao_id'),NEW.ultimo_modificador,'INSERT',now(),TG_RELNAME,NEW.*);
//			
//				RETURN NEW;
//		        -- Registrar quem alterou a linha e quando
//		        ELSIF (TG_OP = 'UPDATE') THEN
//		            INSERT INTO AuditoriaLocacao (cod,dataAlteracao,usuario,tipo_alteracao,tabela,id,data_retirada,data_entrega,data_real_entrega,
//			km_livre,km_atual,km_retorno,valor_diaria,taxa_higiene,taxa_devolucao,preco_final,situacao,veiculo_id,filial_locataria_id,
//			filial_entrega_id,pessoa_id,motorista_id,funcionario_id) values(nextVal('seq_AuditoriaLocacao_id'),NEW.ultimo_modificador,'UPDATE',now(),TG_RELNAME,OLD.*);
//		            RETURN OLD;
//		        END IF;
//		END; $BODY$
//		LANGUAGE plpgsql VOLATILE
//		COST 100;
//
//		CREATE TRIGGER gatilhoAuditoriaLocacao
//				AFTER INSERT OR UPDATE OR DELETE ON Locacao
//				    FOR EACH ROW EXECUTE PROCEDURE auditoriaLocacao();
//
//		update locacao set km_retorno =4 where id = 4
		
		
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
            INSERT INTO Log (id,usuario,registro_antigo,tipo_alteracao,dataalteracao,tabela) values(nextVal('seq_log_id'),user,dados,'UPDATE',now(),TG_RELNAME);
        END IF;
	RETURN OLD;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;*/
	}
	public static class Locacao{
	public String CREATE_VIEW_LOCACAO = "CREATE VIEW VIEWLOCACAO AS SELECT loc.id as cod, loc.data_entrega as dataPrevistaEntrega,loc.data_real_entrega as dataRealEntrega,loc.data_retirada as dataRetirada,"+
		"loc.preco_final as precoFinal,loc.situacao,loc.taxa_devolucao as taxaCombustivel,loc.taxa_higiene as taxaHigiene,loc.valor_diaria as valorDiaria,p.nome as nomeMotorista,motorista.cpf,cliente.nome as nomeCliente, cliente.id as codCliente,v.nome as nomeVeiculo"+
		"FROM Locacao loc"+
		"INNER JOIN Pessoa cliente  on loc.pessoa_id = cliente.id"+
		"INNER JOIN Pessoa_Fisica motorista on loc.motorista_id = motorista.id"+
		"INNER JOIN Pessoa p on p.id = loc.motorista_id "+
		"INNER JOIN Veiculo v on v.id = loc.veiculo_id order by loc.data_retirada order by loc.data_retirada";
	public static String NATIVEQUERY_BUSCARVIEWLOCACAO_POR_PERIODO = "SELECT cod,dataPrevistaEntrega,dataRealEntrega,dataRetirada,nomeCliente,nomeVeiculo,situacao FROM ViewLocacao WHERE dataRetirada >=:var1 and dataRetirada<=:var2";	
	public static String NATIVEQUERY_BUSCARVIEWLOCACAO_POR_CLIENTE = "SELECT cod,codCliente,dataPrevistaEntrega,dataRealEntrega,dataRetirada,nomeCliente,situacao FROM ViewLocacao WHERE codCliente =:var1";	
	public static String NATIVEQUERY_BUSCARVIEWLOCACAO_POR_MOTORISTA = "SELECT cod,cpf,dataPrevistaEntrega,dataRealEntrega,dataRetirada,nomeMotorista,situacao FROM ViewLocacao WHERE cpf =:var1 ORDER BY cod";	
	public static String NATIVEQUERY_BUSCARVIEWLOCACAO_POR_PERIODO_FINACEIRO = "SELECT cod,dataPrevistaEntrega,dataRealEntrega,dataRetirada,situacao,precoFinal,taxaCombustivel,taxaHigiene,valorDiaria FROM ViewLocacao WHERE dataRetirada >=:var1 and dataRetirada<=:var2";	
	//Gatilho
		/**
		 * 
		 CREATE TRIGGER gatilhoLocacao
		AFTER INSERT OR UPDATE OR DELETE ON Locacao
		    FOR EACH ROW EXECUTE PROCEDURE verificaRevisaoVeiculo();

CREATE OR REPLACE FUNCTION verificaRevisaoVeiculo()
  RETURNS trigger AS
$BODY$DECLARE 
	kmRev int:=0;
	kmRestRev int:=0;
	diferenca int:=0;

BEGIN
       IF (TG_OP = 'INSERT') THEN
	   		update Veiculo set status = 'LOCADO' where id = NEW.veiculo_id;
       ELSIF (TG_OP = 'UPDATE') THEN
            Select km_restante_revisao, km_revisao into kmRestRev,kmRev from Veiculo where NEW.veiculo_id = id;
            diferenca = kmRev - kmRestRev;
           	IF(diferenca<0 or diferenca <=2) THEN
				update Veiculo set status = 'REVISAO' where id = NEW.veiculo_id;
				insert into revisao (id,data_hora,status,veiculo_id)values(nextVal('seq_revisao_id'),now(),'ATIVO',NEW.veiculo_id);
	   		ELSE 
				update Veiculo set status = 'ATIVO' where id = NEW.veiculo_id;
	   		END IF;
        END IF;
	RETURN NEW;
END; $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100; **/
	
//-----------------------------------
/**CREATE OR REPLACE FUNCTION auditoriaLocacao()
RETURNS trigger AS
$BODY$
DECLARE 
	nomeuser varChar:= 'NULL';
BEGIN
	IF (TG_OP = 'INSERT') THEN
		INSERT INTO AuditoriaLocacao (cod,dataalteracao,usuario,tipo_alteracao,tabela,data_entrega,data_real_entrega,data_retirada,id,km_atual,km_livre,km_retorno,preco_final,
		situacao,taxa_devolucao,taxa_higiene,valor_diaria,filial_entrega_id,filial_locataria_id,
		funcionario_id ,motorista_id,pessoa_id,veiculo_id) values(nextVal('seq_AuditoriaLocacao_id'),now(),NEW.ultimo_modificador,'INSERT',TG_RELNAME,NEW.data_entrega,NEW.data_real_entrega,NEW.data_retirada,NEW.id,NEW.km_atual,NEW.km_livre,NEW.km_retorno,NEW.preco_final,
		NEW.situacao,NEW.taxa_devolucao,NEW.taxa_higiene,NEW.valor_diaria,NEW.filial_entrega_id,NEW.filial_locataria_id,
		NEW.funcionario_id ,NEW.motorista_id,NEW.pessoa_id,NEW.veiculo_id);
		RETURN NEW;
        -- Registrar quem alterou a linha e quando
        ELSIF (TG_OP = 'UPDATE') THEN
            INSERT INTO AuditoriaLocacao (cod,dataalteracao,usuario,tipo_alteracao,tabela,data_entrega,data_real_entrega,data_retirada,id,km_atual,km_livre,km_retorno,preco_final,
	    situacao,taxa_devolucao,taxa_higiene,valor_diaria,filial_entrega_id,filial_locataria_id,
	    funcionario_id ,motorista_id,pessoa_id,veiculo_id) values(nextVal('seq_AuditoriaLocacao_id'),now(),NEW.ultimo_modificador,'UPDATE',TG_RELNAME,OLD.data_entrega,OLD.data_real_entrega,OLD.data_retirada,OLD.id,OLD.km_atual,OLD.km_livre,OLD.km_retorno,OLD.preco_final,
	    OLD.situacao,OLD.taxa_devolucao,OLD.taxa_higiene,OLD.valor_diaria,OLD.filial_entrega_id,OLD.filial_locataria_id,
	    OLD.funcionario_id ,OLD.motorista_id,OLD.pessoa_id,OLD.veiculo_id);
            RETURN OLD;
        END IF;
END; $BODY$
LANGUAGE plpgsql VOLATILE
COST 100;

CREATE TRIGGER gatilhoAuditoriaLocacao
		AFTER INSERT OR UPDATE OR DELETE ON Locacao
		    FOR EACH ROW EXECUTE PROCEDURE auditoriaLocacao();**/
	}
	public static class Revisao{
		public static String NATIVEQUERY_CHACA_VEICULOS_REVISAO = "select checaVeiculosNaRevisao();";
		public static String NATIVEQUERY_CREATEPROCEDURE_CHECA_VEICULOS_REVISAO = 
				"CREATE OR REPLACE FUNCTION checaVeiculosNaRevisao() RETURNS boolean AS $BODY$"
				+ "DECLARE 	"
				+ "BEGIN"
				+ "UPDATE VEICULO SET status = 'ATIVO', km_restante_revisao = 0  FROM Revisao r WHERE r.veiculo_id = veiculo.id AND (r.data_hora + veiculo.hora_revisao) < NOW() and r.status = 'ATIVO';"
				+ "UPDATE REVISAO SET status = 'FINALIZADA' FROM VEICULO v WHERE revisao.veiculo_id = v.id AND (revisao.data_hora + v.hora_revisao) < NOW() and revisao.status = 'ATIVO';"
				+ "RETURN true;"
				+ "END;$BODY$"
				+ "LANGUAGE plpgsql VOLATILE";
		public static String NATIVEQUERY_CREATE_VIEW_REVISAO = "CREATE VIEW ViewRevisao as SELECT r.id, r.data_hora as horaRevisao,r.status,v.id as codVeiculo,v.nome as nomeVeiculo,v.placa,v.numero_chassi FROM REVISAO r" + 
				" INNER JOIN Veiculo v on v.id = r.veiculo_id";
		public static String  NATIVEQUERYBUSCARVIEWREVISAO_POR_VEICULO = "SELECT cod,horaRevisao,status,codVeiculo,nomeVeiculo,placa,chassi FROM ViewRevisao WHERE codVeiculo =:var1";
		
	}
	public static class Filial{
		public static String NATIVEQUERY_BUSCAR_ID_POR_NOME = "Select p.nome, p.id From Filial p Where p.nome = ?";
	}
	public static class  Categoria{
		public static String NATIVEQUERY_BUSCAR_ID_DISC_POR_NOME = "SELECT p.id, p.discriminador FROM Categoria p WHERE p.nome = ?";
	}
	public static class Veiculo{
		public static String NATIVEQUERY_BUSCAR_ID_POR_NOME = "Select p.nome, p.id From Veiculo p Where p.nome = ?";
		public static String CREATEQUERY_LISTAR_POR_DATA = "select DISTINCT v from Veiculo v, Revisao r, Locacao l Where (v.status Like '%ATIVO%' and v.filialAtual.id=:var2) or " + 
				"((v.id = l.veiculo.id) and (l.dataEntrega <=:var) and (v.status LIKE '%LOCADO%') and (l.filialEntrega.id=:var2)) or " + 
				"((v.id = r.veiculo.id) and (r.dataHora + v.horaRevisao) <=:var and (v.status LIKE '%REVISAO%') and (v.filialAtual.id=:var2))";
	}

}
