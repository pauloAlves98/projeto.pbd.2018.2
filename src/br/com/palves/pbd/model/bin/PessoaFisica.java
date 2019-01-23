package br.com.palves.pbd.model.bin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(name="Pessoa_Fisica.buscarPorCpf",query="Select p from PessoaFisica p where p.cpf =:var"),
	@NamedQuery(name="Pessoa_Fisica.listarPorFiltro",query="Select p from PessoaFisica p where LOWER(p.cpf) LIKE :var or LOWER(p.nome) LIKE :var or"
			+ " LOWER(p.nHabilitacao) LIKE :var or LOWER(p.situacao) LIKE :var or LOWER(p.login) LIKE :var or LOWER(p.endereco.cidade) LIKE :var"),
	@NamedQuery(name="Pessoa_Fisica.listarPorParametro",query="Select p from PessoaFisica p where p.situacao = \'ATIVO\'  and (LOWER(p.cpf) LIKE :var or LOWER(p.nome) LIKE :var or"
			+ " LOWER(p.nHabilitacao) LIKE :var or LOWER(p.login) LIKE :var or LOWER(p.endereco.cidade) LIKE :var or CAST(p.id AS text) LIKE :var)")
})
/**
 * @author Palves
 * */
@Entity
@Table(name="pessoa_fisica")
@PrimaryKeyJoinColumn(name="id") //Identifica qual campo fará essa “junção” entre a tabela pessoa_fisica e a tabela pessoa.
public class PessoaFisica extends Pessoa implements Serializable{
	@NotNull(message="Campo CPF Nulo!")
	@Column(length=14,nullable=false,unique=true)
	private String cpf;
	@Column(length=2)
	private String sexo;
	@Column(name="data_nascimento",nullable=true)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	//@NotNull(message="Campo Nº Habilitação Nulo!")
	@Column(name="n_habilitacao",unique=false)
	private String nHabilitacao;
	@Temporal(TemporalType.DATE)
	private Date dataVencHabilitacao;
	
	public PessoaFisica() {}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getnHabilitacao() {
		return nHabilitacao;
	}

	public void setnHabilitacao(String nHabilitacao) {
		this.nHabilitacao = nHabilitacao;
	}

	public Date getDataVencHabilitacao() {
		return dataVencHabilitacao;
	}

	public void setDataVencHabilitacao(Date dataVencHabilitacao) {
		this.dataVencHabilitacao = dataVencHabilitacao;
	}
	
}
