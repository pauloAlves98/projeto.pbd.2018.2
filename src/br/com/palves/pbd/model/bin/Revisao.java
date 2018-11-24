package br.com.palves.pbd.model.bin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Revisao implements Generico{
	@Id
	@SequenceGenerator(name="seq_revisao",sequenceName="seq_revisao_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_revisao",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@NotNull(message="Campo Data Nulo!")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name ="data_hora")
	private Date dataHora;
	private String status;
	@ManyToOne   //Muitas reservas para uma filial
	@JoinColumn(name="veiculo_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "revisao_veiculo_fkey"))
	private Veiculo veiculo;
	
	public Revisao() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		Revisao other = (Revisao) obj;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Revisao [id=" + id + ", dataHora=" + dataHora + ", status=" + status + ", veiculo=" + veiculo + "]";
	}
	
}
