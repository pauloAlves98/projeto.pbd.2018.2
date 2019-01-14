package br.com.palves.pbd.model.bin;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.palves.pbd.model.complemento.TratadorDeMascara;
@NamedQueries(
		{
			@NamedQuery(name="Filial.listarPorParametro",query="SELECT c FROM Filial c  WHERE c.situacao = \'ATIVO\' and "
					+ "LOWER(c.nome) LIKE :var or CAST(c.id AS text) LIKE :var"),
			@NamedQuery(name="Filial.listarPorFiltro",query="SELECT c FROM Filial c  WHERE LOWER(c.situacao) = :var or "
					+ "LOWER(c.nome) LIKE :var or LOWER(c.endereco.cidade) LIKE :var or CAST(c.id AS text) LIKE :var")
			//@NamedQuery(name="",query="")
		})
@Entity
public class Filial implements Generico{
	@Id
	@SequenceGenerator(name="seq_filial",sequenceName="seq_flial_id",allocationSize=1,initialValue=1)
	@GeneratedValue(generator = "seq_filial",strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column( name= "hora_inicio_expediente")//*DER
	@Temporal(TemporalType.TIME)
	private Date horaInicioExpediente;
	@Column( name= "hora_fim_expediente")
	@Temporal(TemporalType.TIME)//*DER
	private Date horaFimExpediente;
	@NotNull(message="Campo Nome Nulo!")
	@Column(nullable=false,unique=true)
	private String nome;
	@OneToOne(cascade=CascadeType.ALL) //Salva atualiza e deleta
	@JoinColumn(name="endereco_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "endereco_filial_fkey"))
	private Endereco endereco;
	@Column(length=50)//banco
	private String situacao;
	
	public Filial() {
	}
	public Filial(Integer id, @NotNull(message = "Campo Nome Nulo!") String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((horaFimExpediente == null) ? 0 : horaFimExpediente.hashCode());
		result = prime * result + ((horaInicioExpediente == null) ? 0 : horaInicioExpediente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	public Date getHoraInicioExpediente() {
		return horaInicioExpediente;
	}

	public void setHoraInicioExpediente(Date dataInicioExpediente) {
		this.horaInicioExpediente = dataInicioExpediente;
	}

	public Date getDataFimExpediente() {
		return horaFimExpediente;
	}

	public void setDataFimExpediente(Date dataFimExpediente) {
		this.horaFimExpediente = dataFimExpediente;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filial other = (Filial) obj;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (horaFimExpediente == null) {
			if (other.horaFimExpediente != null)
				return false;
		} else if (!horaFimExpediente.equals(other.horaFimExpediente))
			return false;
		if (horaInicioExpediente == null) {
			if (other.horaInicioExpediente != null)
				return false;
		} else if (!horaInicioExpediente.equals(other.horaInicioExpediente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}

	public Date getHoraFimExpediente() {
		return horaFimExpediente;
	}

	public void setHoraFimExpediente(Date horaFimExpediente) {
		this.horaFimExpediente = horaFimExpediente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@Override
	public String toString() {
		return "Filial [id=" + id + ", horaInicioExpediente=" + horaInicioExpediente + ", horaFimExpediente="
				+ horaFimExpediente + ", nome=" + nome + ", endereco=" + endereco + ", situacao=" + situacao + "]";
	}
	public String toStringArea() {
		return  "COD: "+ id + "\nExpediente: " + TratadorDeMascara.converterHoraString(this.horaInicioExpediente) + " as "
				+ TratadorDeMascara.converterHoraString(this.horaFimExpediente) + "\nNome: " + nome + "\nEndereco\nBairro: " + endereco.getBairro() +"\nCep: "+endereco.getCep()+
				"\nCidade: "+endereco.getCidade()+"\nRua: "+endereco.getRua()+"\nNumero: "+endereco.getNumero()+"\nUF: "+endereco.getUf()+"\nSituacao: " + situacao + "";
	}
	public String formatada(String s) {
		return "<html>"+"<b>"+s+"</b></html>";
	}
}
