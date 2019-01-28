package br.com.palves.pbd.model.bin;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.com.palves.pbd.sql.SQLUtil;

/**
 * @author Palves
 * */
//@NamedQueries({
//	@NamedQuery(name = "Pessoa.buscarLogin",query="Select p from Pessoa p Where p.login =")
//})
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa implements Serializable,Generico{
	@Id
	@SequenceGenerator(name="seq_pessoa",sequenceName="seq_pessoa_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_pessoa",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@NotNull(message="Campo Nome Nulo!")
	@Column(nullable=false)
	private String nome;
	@NotNull(message="Campo Login Nulo!")
	@Column(unique=true,nullable=false)
	private String login;
	@NotNull(message="Campo Senha Nulo!")
	@Column(length=50)
	private String senha;
	@Column(length=50)
	private String situacao;
	@Column(length=50)
	private String discriminador;
	@Column(name="ultimo_modificador")
	private String ultimoModificador;
	@OneToOne(cascade=CascadeType.ALL) //Salva atualiza e deleta
	@JoinColumn(name="endereco_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "endereco_fkey"))
	private Endereco endereco;
	
	public Pessoa() {
		
	}
	public String getDiscriminador() {
		return discriminador;
	}

	public void setDiscriminador(String discriminador) {
		this.discriminador = discriminador;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discriminador == null) ? 0 : discriminador.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((ultimoModificador == null) ? 0 : ultimoModificador.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (discriminador == null) {
			if (other.discriminador != null)
				return false;
		} else if (!discriminador.equals(other.discriminador))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (ultimoModificador == null) {
			if (other.ultimoModificador != null)
				return false;
		} else if (!ultimoModificador.equals(other.ultimoModificador))
			return false;
		return true;
	}
	public String getUltimoModificador() {
		return ultimoModificador;
	}
	public void setUltimoModificador(String ultimoModificador) {
		this.ultimoModificador = ultimoModificador;
	}
}
