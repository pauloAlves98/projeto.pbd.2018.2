package br.com.palves.pbd.model.bin;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@NamedQueries({
	@NamedQuery(name="PessoaJuridica.buscarPorCnpj",query="Select pj from PessoaJuridica pj where pj.cnpj =:var"),
	@NamedQuery(name="PessoaJuridica.listarPorFiltro",query="Select p from PessoaJuridica p where LOWER(p.cnpj) LIKE :var or LOWER(p.nome) LIKE :var or"
			+ " LOWER(p.incEstadual) LIKE :var or LOWER(p.situacao) LIKE :var or LOWER(p.login) LIKE :var or LOWER(p.endereco.cidade) LIKE :var")
})
@Entity
@Table(name="pessoa_juridica")
@PrimaryKeyJoinColumn(name="id")
public class PessoaJuridica extends Pessoa implements Serializable{
	@Column(length=18,nullable=false,unique=true)
	private String cnpj;
	@Column(name="inc_estadual")
	private String incEstadual;
	
	public PessoaJuridica() {}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIncEstadual() {
		return incEstadual;
	}

	public void setIncEstadual(String incEstadual) {
		this.incEstadual = incEstadual;
	}
}
