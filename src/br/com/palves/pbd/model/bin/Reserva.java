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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@NamedQueries(
		{
			@NamedQuery(name="Reserva.listarPorFiltro",query="SELECT c FROM Reserva c WHERE c.pessoa.id =:var4 and dataHoraRetirada BETWEEN :var2 and :var3 and (LOWER(c.situacao) LIKE :var1 or LOWER(c.categoria.nome) LIKE :var1)"),
			@NamedQuery(name="Reserva.listarPorStatus",query="SELECT c FROM Reserva c WHERE c.situacao =:var1"),
			@NamedQuery(name="Reserva.listarPorParametroClienteData",query="SELECT c FROM Reserva c WHERE c.pessoa.id=:var and dataHoraRetirada BETWEEN :var2 and :var3"),
			@NamedQuery(name="Reserva.listarPorParametroCliente",query="SELECT c FROM Reserva c WHERE c.pessoa.id =:var"),
			@NamedQuery(name="Reserva.listarPorParametroData",query="SELECT c FROM Reserva c WHERE dataHoraRetirada BETWEEN :var1 and :var2")
		})
@Entity
public class Reserva implements Generico{
	@Id
	@SequenceGenerator(name="seq_reserva",sequenceName="seq_reserva_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_reserva",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_reserva", nullable=false)//Olhar se eh realmente necessario
	private Date dataHoraReserva;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_retirada", nullable=false)
	private Date dataHoraRetirada;
	@Column(name="valor_diaria_klivre")
	private double valorDiariaKlivre;
	@Column(name="valor_diaria_kcontrole")
	private double valorDiariaKcontrole;
	private String situacao;
	@ManyToOne   //Muitas reservas para uma pessoa
	@JoinColumn(name="pessoa_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "reserva_pessoa_fkey"),nullable=true)
	private Pessoa pessoa;
	@ManyToOne   //Muitas reservas para uma categoria
	@JoinColumn(name="categoria_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "reserva_categoria_fkey"))
	private Categoria categoria;
	@ManyToOne   //Muitas reservas para uma filial
	@JoinColumn(name="filial_retirada_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "reserva_filial_fkey"))
	private Filial filialRetirada;
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataHoraReserva() {
		return dataHoraReserva;
	}
	public void setDataHoraReserva(Date dataHoraReserva) {
		this.dataHoraReserva = dataHoraReserva;
	}
	public Date getDataHoraRetirada() {
		return dataHoraRetirada;
	}
	public void setDataHoraRetirada(Date dataHoraRetirada) {
		this.dataHoraRetirada = dataHoraRetirada;
	}
	public double getValorDiariaKlivre() {
		return valorDiariaKlivre;
	}
	public void setValorDiariaKlivre(double valorDiariaKlivre) {
		this.valorDiariaKlivre = valorDiariaKlivre;
	}
	public double getValorDiariaKcontrole() {
		return valorDiariaKcontrole;
	}
	public void setValorDiariaKcontrole(double valorDiariaKcontrole) {
		this.valorDiariaKcontrole = valorDiariaKcontrole;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Filial getFilial() {
		return filialRetirada;
	}
	public void setFilial(Filial filial) {
		this.filialRetirada = filial;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dataHoraReserva == null) ? 0 : dataHoraReserva.hashCode());
		result = prime * result + ((dataHoraRetirada == null) ? 0 : dataHoraRetirada.hashCode());
		result = prime * result + ((filialRetirada == null) ? 0 : filialRetirada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorDiariaKcontrole);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorDiariaKlivre);
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
		Reserva other = (Reserva) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dataHoraReserva == null) {
			if (other.dataHoraReserva != null)
				return false;
		} else if (!dataHoraReserva.equals(other.dataHoraReserva))
			return false;
		if (dataHoraRetirada == null) {
			if (other.dataHoraRetirada != null)
				return false;
		} else if (!dataHoraRetirada.equals(other.dataHoraRetirada))
			return false;
		if (filialRetirada == null) {
			if (other.filialRetirada != null)
				return false;
		} else if (!filialRetirada.equals(other.filialRetirada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		if (Double.doubleToLongBits(valorDiariaKcontrole) != Double.doubleToLongBits(other.valorDiariaKcontrole))
			return false;
		if (Double.doubleToLongBits(valorDiariaKlivre) != Double.doubleToLongBits(other.valorDiariaKlivre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", dataHoraReserva=" + dataHoraReserva + ", dataHoraRetirada=" + dataHoraRetirada
				+ ", valorDiariaKlivre=" + valorDiariaKlivre + ", valorDiariaKcontrole=" + valorDiariaKcontrole
				+ ", situacao=" + situacao + ", pessoa=" + pessoa + ", categoria=" + categoria + ", filial=" + filialRetirada
				+ "]";
	}
	
	
	
	
}
