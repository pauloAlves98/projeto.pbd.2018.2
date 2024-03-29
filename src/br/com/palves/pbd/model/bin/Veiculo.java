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
import javax.validation.constraints.NotNull;
@NamedQueries(
		{
			@NamedQuery(name="Veiculo.listarPorFiltro",query="SELECT c FROM Veiculo c WHERE (LOWER(c.status) LIKE :var1 or LOWER(c.numeroChassi) LIKE :var1 or LOWER(c.nome) LIKE :var1 or LOWER(c.tamanho) LIKE :var1 or "
			+ "LOWER(c.tipoCombustivel) LIKE :var1 or LOWER(c.numeroMotor) LIKE :var1 or LOWER(c.modelo) LIKE :var1 or LOWER(c.fabricante) LIKE :var1 or LOWER(c.placa) LIKE :var1 or "
			+ "LOWER(c.categoria.nome) LIKE :var1 or LOWER(c.filialAtual.nome) LIKE :var1 or LOWER(c.categoria.horaLimpeza) LIKE :var1 or LOWER(c.categoria.tipoCambio) LIKE :var1 or CAST(c.id AS text) LIKE :var1)"),
			@NamedQuery(name="Veiculo.listarPorParametroFilialCategoria",query="SELECT c FROM Veiculo c WHERE c.categoria.id =:var3 and c.filialAtual.id =:var2 and  c.categoria.discriminador =:var4 and ((LOWER(c.status) LIKE :var1 or LOWER(c.numeroChassi) LIKE :var1 or LOWER(c.nome) LIKE :var1 or LOWER(c.tamanho) LIKE :var1 or "
					+ "LOWER(c.tipoCombustivel) LIKE :var1 or LOWER(c.numeroMotor) LIKE :var1 or LOWER(c.modelo) LIKE :var1 or LOWER(c.fabricante) LIKE :var1 or LOWER(c.placa) LIKE :var1 or "
					+ "LOWER(c.categoria.nome) LIKE :var1 or LOWER(c.filialAtual.nome) LIKE :var1 or LOWER(c.categoria.horaLimpeza) LIKE :var1 or LOWER(c.categoria.tipoCambio) LIKE :var1 or CAST(c.id AS text) LIKE :var1))"),
			@NamedQuery(name="Veiculo.listarPorParametroFilialCategoriaGerente",query="SELECT c FROM Veiculo c WHERE c.categoria.valor >=:var3 and c.filialAtual.id =:var2 and ((LOWER(c.status) LIKE :var1 or LOWER(c.numeroChassi) LIKE :var1 or LOWER(c.nome) LIKE :var1 or LOWER(c.tamanho) LIKE :var1 or "
					+ "LOWER(c.tipoCombustivel) LIKE :var1 or LOWER(c.numeroMotor) LIKE :var1 or LOWER(c.modelo) LIKE :var1 or LOWER(c.fabricante) LIKE :var1 or LOWER(c.placa) LIKE :var1 or "
					+ "LOWER(c.categoria.nome) LIKE :var1 or LOWER(c.filialAtual.nome) LIKE :var1 or LOWER(c.categoria.horaLimpeza) LIKE :var1 or LOWER(c.categoria.tipoCambio) LIKE :var1 or CAST(c.id AS text) LIKE :var1))"),
			@NamedQuery(name="Veiculo.listarPorFilialStatus",query="SELECT c FROM Veiculo c WHERE c.status LIKE :var3 and c.filialAtual.id =:var2 and (LOWER(c.numeroChassi) LIKE :var1 or LOWER(c.nome) LIKE :var1 or LOWER(c.tamanho) LIKE :var1 or "
					+ "LOWER(c.tipoCombustivel) LIKE :var1 or LOWER(c.numeroMotor) LIKE :var1 or LOWER(c.modelo) LIKE :var1 or LOWER(c.fabricante) LIKE :var1 or LOWER(c.placa) LIKE :var1 or "
					+ "LOWER(c.categoria.nome) LIKE :var1 or LOWER(c.filialAtual.nome) LIKE :var1 or LOWER(c.categoria.horaLimpeza) LIKE :var1 or LOWER(c.categoria.tipoCambio) LIKE :var1 or CAST(c.id AS text) LIKE :var1)")
//			@NamedQuery(name="Veiculo.listarPorData",query="select DISTINCT v from Veiculo v,Revisao r,Locacao l Where v.status Like '%ATIVO%' or " + 
//					"(v.id = l.veiculo and l.dataEntrega <=: var and v.status = 'LOCADO') or " + 
//					"(v.id = r.veiculoand r.dataHora + v.horaRevisao <= var and v.status = 'REVISAO')" )
			//@NamedQuery(name="",query="")
		})
@Entity
public class Veiculo implements Generico{
	@Id
	@SequenceGenerator(name="seq_veiculo",sequenceName="seq_veiculo_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_veiculo",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="numero_chassi",unique=true,length=17)
	private String numeroChassi;
	@Column(length=100)
	private String nome;
	@Column(name="n_porta")
	private int nPorta;
	@Column(name="placa",unique=true,length=8)
	private String placa;
	@Column(name="tipo_combustivel",length=50)
	private String tipoCombustivel;
	@NotNull(message="Campo Tamanho Nulo!")
	@Column(nullable=false)
	private String tamanho;
	@Column(name="km_Revisao")
	private int kmRevisao;
	@Column(name="torque_motor")
	private String torqueMotor;
	@Column(name="numero_motor",length=30)
	private String numeroMotor;
	private String modelo;
	@Column(name="ano_modelo")
	private int anoModelo;
	private String cor;
	private String fabricante;
	@Column(name="ano_fabricacao")
	private int anoFabricao;
	@Column(name="km_atual")
	private int kmAtual;
	@Column(name="km_restante_revisao")
	private int kmRestanteRevisao;
	@Column(name="hora_revisao")
	@Temporal(TemporalType.TIME)
	private Date horaRevisao;
	@Column(length=50)
	private String status;
	@ManyToOne   //Muitos veiculos para uma filial atual;
	@JoinColumn(name="filial_atual_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "veiculo_filialAtual_fkey"))
	private Filial filialAtual;
	@ManyToOne   //Muitos veiculos para uma categoria;
	@JoinColumn(name="categoria_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "veiculo_categoria_fkey"))
	private Categoria categoria;
	public Veiculo() {}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumeroChassi() {
		return numeroChassi;
	}


	public void setNumeroChassi(String numeroChassi) {
		this.numeroChassi = numeroChassi;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getnPorta() {
		return nPorta;
	}


	public void setnPorta(int nPorta) {
		this.nPorta = nPorta;
	}


	public String getTipoCombustivel() {
		return tipoCombustivel;
	}


	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}


	public String getTamanho() {
		return tamanho;
	}


	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}


	public int getKm_revisao() {
		return kmRevisao;
	}


	public void setKm_revisao(int km_revisao) {
		this.kmRevisao = km_revisao;
	}


	public String getTorqueMotor() {
		return torqueMotor;
	}


	public void setTorqueMotor(String torqueMotor) {
		this.torqueMotor = torqueMotor;
	}


	public String getNumeroMotor() {
		return numeroMotor;
	}


	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getAnoModelo() {
		return anoModelo;
	}


	public void setAnoModelo(int anoModelo) {
		this.anoModelo = anoModelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getFabricante() {
		return fabricante;
	}


	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}


	public int getAnoFabricao() {
		return anoFabricao;
	}


	public void setAnoFabricao(int anoFabricao) {
		this.anoFabricao = anoFabricao;
	}


	public int getKmAtual() {
		return kmAtual;
	}


	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}


	public int getKmRestanteRevisao() {
		return kmRestanteRevisao;
	}


	public void setKmRestanteRevisao(int kmRestanteRevisao) {
		this.kmRestanteRevisao = kmRestanteRevisao;
	}


	public Date getHoraRevisao() {
		return horaRevisao;
	}


	public void setHoraRevisao(Date horaRevisao) {
		this.horaRevisao = horaRevisao;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Filial getFilialAtual() {
		return filialAtual;
	}


	public void setFilialAtual(Filial filialAtual) {
		this.filialAtual = filialAtual;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anoFabricao;
		result = prime * result + anoModelo;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((filialAtual == null) ? 0 : filialAtual.hashCode());
		result = prime * result + ((horaRevisao == null) ? 0 : horaRevisao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + kmAtual;
		result = prime * result + kmRestanteRevisao;
		result = prime * result + kmRevisao;
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + nPorta;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroChassi == null) ? 0 : numeroChassi.hashCode());
		result = prime * result + ((numeroMotor == null) ? 0 : numeroMotor.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
		result = prime * result + ((tipoCombustivel == null) ? 0 : tipoCombustivel.hashCode());
		result = prime * result + ((torqueMotor == null) ? 0 : torqueMotor.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (anoFabricao != other.anoFabricao)
			return false;
		if (anoModelo != other.anoModelo)
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (filialAtual == null) {
			if (other.filialAtual != null)
				return false;
		} else if (!filialAtual.equals(other.filialAtual))
			return false;
		if (horaRevisao == null) {
			if (other.horaRevisao != null)
				return false;
		} else if (!horaRevisao.equals(other.horaRevisao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kmAtual != other.kmAtual)
			return false;
		if (kmRestanteRevisao != other.kmRestanteRevisao)
			return false;
		if (kmRevisao != other.kmRevisao)
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nPorta != other.nPorta)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroChassi == null) {
			if (other.numeroChassi != null)
				return false;
		} else if (!numeroChassi.equals(other.numeroChassi))
			return false;
		if (numeroMotor == null) {
			if (other.numeroMotor != null)
				return false;
		} else if (!numeroMotor.equals(other.numeroMotor))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tamanho == null) {
			if (other.tamanho != null)
				return false;
		} else if (!tamanho.equals(other.tamanho))
			return false;
		if (tipoCombustivel == null) {
			if (other.tipoCombustivel != null)
				return false;
		} else if (!tipoCombustivel.equals(other.tipoCombustivel))
			return false;
		if (torqueMotor == null) {
			if (other.torqueMotor != null)
				return false;
		} else if (!torqueMotor.equals(other.torqueMotor))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", numeroChassi=" + numeroChassi + ", nome=" + nome + ", nPorta=" + nPorta
				+ ", placa=" + placa + ", tipoCombustivel=" + tipoCombustivel + ", tamanho=" + tamanho + ", kmRevisao="
				+ kmRevisao + ", torqueMotor=" + torqueMotor + ", numeroMotor=" + numeroMotor + ", modelo=" + modelo
				+ ", anoModelo=" + anoModelo + ", cor=" + cor + ", fabricante=" + fabricante + ", anoFabricao="
				+ anoFabricao + ", kmAtual=" + kmAtual + ", kmRestanteRevisao=" + kmRestanteRevisao + ", horaRevisao="
				+ horaRevisao + ", status=" + status + ", filialAtual=" + filialAtual + ", categoria=" + categoria
				+ "]";
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public int getKmRevisao() {
		return kmRevisao;
	}
	public void setKmRevisao(int kmRevisao) {
		this.kmRevisao = kmRevisao;
	}
}
