package br.com.palves.pbd.model.bin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Configuracao implements Generico {
	@Id
	@SequenceGenerator(name="seq_configuracao",sequenceName="seq_configuracao_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_configuracao",strategy=GenerationType.SEQUENCE)
	private Integer id;
	private Date dataRealizacao;
	@Column(name="diaria_klivre")
	private double diariaKlivre;
	@Column(name="diaria_kcontrole")
	private double diariaKcontrole;
	@Column(name="taxa_higiene")
	private double taxaHigiene;
	@Column(name="taxa_combustivel")
	private double taxaCombustivel;
	@Column(name="taxa_devolucao")
	private double taxaDevolucao;
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
	public Date getDataRealizacao() {
		return dataRealizacao;
	}
	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
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
	public double getTaxaHigiene() {
		return taxaHigiene;
	}
	public void setTaxaHigiene(double taxaHigiene) {
		this.taxaHigiene = taxaHigiene;
	}
	public double getTaxaCombustivel() {
		return taxaCombustivel;
	}
	public void setTaxaCombustivel(double taxaCombustivel) {
		this.taxaCombustivel = taxaCombustivel;
	}
	public double getTaxaDevolucao() {
		return taxaDevolucao;
	}
	public void setTaxaDevolucao(double taxaDevolucao) {
		this.taxaDevolucao = taxaDevolucao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataRealizacao == null) ? 0 : dataRealizacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(diariaKcontrole);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(diariaKlivre);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(taxaCombustivel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taxaDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taxaHigiene);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (dataRealizacao == null) {
			if (other.dataRealizacao != null)
				return false;
		} else if (!dataRealizacao.equals(other.dataRealizacao))
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
		if (Double.doubleToLongBits(taxaCombustivel) != Double.doubleToLongBits(other.taxaCombustivel))
			return false;
		if (Double.doubleToLongBits(taxaDevolucao) != Double.doubleToLongBits(other.taxaDevolucao))
			return false;
		if (Double.doubleToLongBits(taxaHigiene) != Double.doubleToLongBits(other.taxaHigiene))
			return false;
		return true;
	}

}
