package br.com.palves.pbd.model.bin.auditoria;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.palves.pbd.model.bin.Endereco;
@Entity
public class AuditoriaPessoa {
	@Id
	@SequenceGenerator(name="seq_AuditoriaPessoa",sequenceName="seq_AuditoriaPessoa_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_AuditoriaPessoa",strategy=GenerationType.SEQUENCE)
	private Integer cod;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	private String usuario;
	@Column(name="tipo_alteracao", length=30)
	private String tipoAlteracao;
	@Column(length=30)
	private String tabela;
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
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTipoAlteracao() {
		return tipoAlteracao;
	}
	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
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
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getDiscriminador() {
		return discriminador;
	}
	public void setDiscriminador(String discriminador) {
		this.discriminador = discriminador;
	}
	public String getUltimoModificador() {
		return ultimoModificador;
	}
	public void setUltimoModificador(String ultimoModificador) {
		this.ultimoModificador = ultimoModificador;
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
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((discriminador == null) ? 0 : discriminador.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
		result = prime * result + ((tipoAlteracao == null) ? 0 : tipoAlteracao.hashCode());
		result = prime * result + ((ultimoModificador == null) ? 0 : ultimoModificador.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		AuditoriaPessoa other = (AuditoriaPessoa) obj;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
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
		if (tabela == null) {
			if (other.tabela != null)
				return false;
		} else if (!tabela.equals(other.tabela))
			return false;
		if (tipoAlteracao == null) {
			if (other.tipoAlteracao != null)
				return false;
		} else if (!tipoAlteracao.equals(other.tipoAlteracao))
			return false;
		if (ultimoModificador == null) {
			if (other.ultimoModificador != null)
				return false;
		} else if (!ultimoModificador.equals(other.ultimoModificador))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
