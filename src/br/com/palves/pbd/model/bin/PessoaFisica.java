package br.com.palves.pbd.model.bin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@NotNull(message="Campo Nº Habilitação Nulo!")
	@Column(name="n_habilitacao",nullable=false,unique=true)
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
