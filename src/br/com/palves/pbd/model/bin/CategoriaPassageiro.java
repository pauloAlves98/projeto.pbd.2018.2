package br.com.palves.pbd.model.bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
@NamedQueries(
		{
			@NamedQuery(name="CategoriaPassageiro.listarPorFiltro",query="SELECT c FROM CategoriaPassageiro c WHERE LOWER(c.discriminador) = \'cp\' and (LOWER(c.situacao) LIKE :var1 or LOWER(c.nome) LIKE :var1 "
					+ "or LOWER(c.horaLimpeza) LIKE :var1 or LOWER(c.tipoCambio) LIKE :var1 or LOWER(c.tipoAirBag) LIKE :var1 or CAST(c.id AS text) LIKE :var1)")
			//@NamedQuery(name="",query="")
		})
@Entity
@Table(name="categoria_passageiro")
@PrimaryKeyJoinColumn(name="id")
public class CategoriaPassageiro extends Categoria{
	@Column(name="tipo_air_bag")
	private String tipoAirBag;
	@Column(name="direcao_assistida")
	private boolean direcaoAssistida;
	@Column(name="cinto_seguranca_traseiro")
	private boolean cintoSeguancaTraseiro;
	@Column(name="roda_liga_leve")
	private boolean rodaLigaLeve;
	@Column(name="controle_poluicao_ar")
	private boolean controlePoluicaoAr;
	
	public CategoriaPassageiro() {}

	public String getTipoAirBag() {
		return tipoAirBag;
	}

	public void setTipoAirBag(String tipoAirBag) {
		this.tipoAirBag = tipoAirBag;
	}

	public boolean isDirecaoAssistida() {
		return direcaoAssistida;
	}

	public void setDirecaoAssistida(boolean direcaoAssistida) {
		this.direcaoAssistida = direcaoAssistida;
	}

	public boolean isCintoSeguancaTraseiro() {
		return cintoSeguancaTraseiro;
	}

	public void setCintoSeguancaTraseiro(boolean cintoSeguancaTraseiro) {
		this.cintoSeguancaTraseiro = cintoSeguancaTraseiro;
	}

	public boolean isRodaLigaLeve() {
		return rodaLigaLeve;
	}

	public void setRodaLigaLeve(boolean rodaLigaLeve) {
		this.rodaLigaLeve = rodaLigaLeve;
	}

	public boolean isControlePoluicaoAr() {
		return controlePoluicaoAr;
	}

	public void setControlePoluicaoAr(boolean controlePoluicaoAr) {
		this.controlePoluicaoAr = controlePoluicaoAr;
	}

	@Override
	public String toString() {
		return (super.toString()+"\nAir Bag: " + tipoAirBag + "\nDirecao Assistida: " + direcaoAssistida
				+ "\nCinto Traseiro: " + cintoSeguancaTraseiro + "\nRoda Liga Leve: " + rodaLigaLeve
				+ "\nControler Poluicao Ar: " + controlePoluicaoAr + "").replace("false","Não").replace("true","Sim");
	}
	
	
}
