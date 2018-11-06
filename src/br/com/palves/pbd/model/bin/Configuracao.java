package br.com.palves.pbd.model.bin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Configuracao {
	@Id
	@SequenceGenerator(name="seq_configuracao",sequenceName="seq_configuracao_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_configuracao",strategy=GenerationType.SEQUENCE)
	private Integer id;
	private Date data;
	@Column(name="diaria_klivre")
	private double diariaKlivre;
	@Column(name="diaria_kcontrole")
	private double diariaKcontrole;
	@Column(name="taxa_higiene")
	private int taxaHigiene;
	@Column(name="taxa_combustivel")
	private int taxaCombustivel;
	@Column(name="taxa_devolucao")
	private int taxaDevolucao;
	public Configuracao() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getDiariaKlivre() {
		return diariaKlivre;
	}
	public void setDiariaKlivre(double diariaKlivre) {
		this.diariaKlivre = diariaKlivre;
	}
	public double getDiariaKcontrole() {
		return diariaKcontrole;
	}
	public void setDiariaKcontrole(double diariaKcontrole) {
		this.diariaKcontrole = diariaKcontrole;
	}
	public int getTaxaHigiene() {
		return taxaHigiene;
	}
	public void setTaxaHigiene(int taxaHigiene) {
		this.taxaHigiene = taxaHigiene;
	}
	public int getTaxaCombustivel() {
		return taxaCombustivel;
	}
	public void setTaxaCombustivel(int taxaCombustivel) {
		this.taxaCombustivel = taxaCombustivel;
	}
	public int getTaxaDevolucao() {
		return taxaDevolucao;
	}
	public void setTaxaDevolucao(int taxaDevolucao) {
		this.taxaDevolucao = taxaDevolucao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		long temp;
		temp = Double.doubleToLongBits(diariaKcontrole);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(diariaKlivre);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + taxaCombustivel;
		result = prime * result + taxaDevolucao;
		result = prime * result + taxaHigiene;
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
		Configuracao other = (Configuracao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (Double.doubleToLongBits(diariaKcontrole) != Double.doubleToLongBits(other.diariaKcontrole))
			return false;
		if (Double.doubleToLongBits(diariaKlivre) != Double.doubleToLongBits(other.diariaKlivre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (taxaCombustivel != other.taxaCombustivel)
			return false;
		if (taxaDevolucao != other.taxaDevolucao)
			return false;
		if (taxaHigiene != other.taxaHigiene)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Configuracao [id=" + id + ", data=" + data + ", diariaKlivre=" + diariaKlivre + ", diariaKcontrole="
				+ diariaKcontrole + ", taxaHigiene=" + taxaHigiene + ", taxaCombustivel=" + taxaCombustivel
				+ ", taxaDevolucao=" + taxaDevolucao + "]";
	}
}
