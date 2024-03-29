package br.com.palves.pbd.model.bin.auditoria;

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

import br.com.palves.pbd.model.bin.Filial;
import br.com.palves.pbd.model.bin.Funcionario;
import br.com.palves.pbd.model.bin.Pessoa;
import br.com.palves.pbd.model.bin.PessoaFisica;
import br.com.palves.pbd.model.bin.Veiculo;

@Entity
public class AuditoriaLocacao {
	@Id
	@SequenceGenerator(name="seq_AuditoriaLocacao",sequenceName="seq_AuditoriaLocacao_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_AuditoriaLocacao",strategy=GenerationType.SEQUENCE)
	private Integer cod;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	private String usuario;
	@Column(name="tipo_alteracao", length=30)
	private String tipoAlteracao;
	@Column(length=30)
	private String tabela;
	private Integer id;
	@NotNull(message="Campo Data Retirada Nulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, name= "data_retirada")
	private Date dataRetirada;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "data_entrega") //DATA PREVISTA PARA ENTREGA
	private Date dataEntrega;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "data_real_entrega")
	private Date dataRealEntrega;
	@Column(name="km_livre")
	private boolean kmLivre;
	@Column(name="km_atual")
	private int kmAtual;
	@Column(name="km_retorno")
	private int kmRetorno;
	@Column(name="valor_diaria")
	private double valorDiaria;
	@Column(name="taxa_higiene")
	private double taxaHigiene;
	@Column(name="taxa_devolucao")
	private double taxaDevolucao;//Taxa combustivel
//	@Column(name="taxa_combustivel")
//	private double taxaCombustivel;//Taxa combustivel Combustivel
	@Column(name="preco_final")
	private double precoFinal;
	private String situacao;
	@ManyToOne   
	@JoinColumn(name="veiculo_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_veiculo_fkey"))
	private Veiculo veiculo;
	@ManyToOne   
	@JoinColumn(name="filial_locataria_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_filialLocataria_fkey"))
	private Filial filialLocaataria;
	@ManyToOne   
	@JoinColumn(name="filial_entrega_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_filialEntrega_fkey"))
	private Filial filialEntrega;
	@ManyToOne  
	@JoinColumn(name="pessoa_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_pessoa_fkey"))
	private Pessoa pessoa;
	@ManyToOne   
	@JoinColumn(name="motorista_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_motorista_fkey"))
	private PessoaFisica motorista;
	@ManyToOne   //Muitas Loca�oes para uma func
	@JoinColumn(name="funcionario_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "locacao_funcionario_fkey"))
	private Funcionario funcionario;
	public AuditoriaLocacao() {
		
	}
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
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Date getDataRealEntrega() {
		return dataRealEntrega;
	}
	public void setDataRealEntrega(Date dataRealEntrega) {
		this.dataRealEntrega = dataRealEntrega;
	}
	public boolean isKmLivre() {
		return kmLivre;
	}
	public void setKmLivre(boolean kmLivre) {
		this.kmLivre = kmLivre;
	}
	public int getKmAtual() {
		return kmAtual;
	}
	public void setKmAtual(int kmAtual) {
		this.kmAtual = kmAtual;
	}
	public int getKmRetorno() {
		return kmRetorno;
	}
	public void setKmRetorno(int kmRetorno) {
		this.kmRetorno = kmRetorno;
	}
	public double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public double getTaxaHigiene() {
		return taxaHigiene;
	}
	public void setTaxaHigiene(double taxaHigiene) {
		this.taxaHigiene = taxaHigiene;
	}
	public double getTaxaDevolucao() {
		return taxaDevolucao;
	}
	public void setTaxaDevolucao(double taxaDevolucao) {
		this.taxaDevolucao = taxaDevolucao;
	}
	public double getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Filial getFilialLocaataria() {
		return filialLocaataria;
	}
	public void setFilialLocaataria(Filial filialLocaataria) {
		this.filialLocaataria = filialLocaataria;
	}
	public Filial getFilialEntrega() {
		return filialEntrega;
	}
	public void setFilialEntrega(Filial filialEntrega) {
		this.filialEntrega = filialEntrega;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	public PessoaFisica getMotorista() {
		return motorista;
	}
	public void setMotorista(PessoaFisica motorista) {
		this.motorista = motorista;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataEntrega == null) ? 0 : dataEntrega.hashCode());
		result = prime * result + ((dataRealEntrega == null) ? 0 : dataRealEntrega.hashCode());
		result = prime * result + ((dataRetirada == null) ? 0 : dataRetirada.hashCode());
		result = prime * result + ((filialEntrega == null) ? 0 : filialEntrega.hashCode());
		result = prime * result + ((filialLocaataria == null) ? 0 : filialLocaataria.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + kmAtual;
		result = prime * result + (kmLivre ? 1231 : 1237);
		result = prime * result + kmRetorno;
		result = prime * result + ((motorista == null) ? 0 : motorista.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoFinal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((tabela == null) ? 0 : tabela.hashCode());
		temp = Double.doubleToLongBits(taxaDevolucao);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(taxaHigiene);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoAlteracao == null) ? 0 : tipoAlteracao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		temp = Double.doubleToLongBits(valorDiaria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		AuditoriaLocacao other = (AuditoriaLocacao) obj;
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
		if (dataEntrega == null) {
			if (other.dataEntrega != null)
				return false;
		} else if (!dataEntrega.equals(other.dataEntrega))
			return false;
		if (dataRealEntrega == null) {
			if (other.dataRealEntrega != null)
				return false;
		} else if (!dataRealEntrega.equals(other.dataRealEntrega))
			return false;
		if (dataRetirada == null) {
			if (other.dataRetirada != null)
				return false;
		} else if (!dataRetirada.equals(other.dataRetirada))
			return false;
		if (filialEntrega == null) {
			if (other.filialEntrega != null)
				return false;
		} else if (!filialEntrega.equals(other.filialEntrega))
			return false;
		if (filialLocaataria == null) {
			if (other.filialLocaataria != null)
				return false;
		} else if (!filialLocaataria.equals(other.filialLocaataria))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kmAtual != other.kmAtual)
			return false;
		if (kmLivre != other.kmLivre)
			return false;
		if (kmRetorno != other.kmRetorno)
			return false;
		if (motorista == null) {
			if (other.motorista != null)
				return false;
		} else if (!motorista.equals(other.motorista))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (Double.doubleToLongBits(precoFinal) != Double.doubleToLongBits(other.precoFinal))
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
		if (Double.doubleToLongBits(taxaDevolucao) != Double.doubleToLongBits(other.taxaDevolucao))
			return false;
		if (Double.doubleToLongBits(taxaHigiene) != Double.doubleToLongBits(other.taxaHigiene))
			return false;
		if (tipoAlteracao == null) {
			if (other.tipoAlteracao != null)
				return false;
		} else if (!tipoAlteracao.equals(other.tipoAlteracao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (Double.doubleToLongBits(valorDiaria) != Double.doubleToLongBits(other.valorDiaria))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}
}
