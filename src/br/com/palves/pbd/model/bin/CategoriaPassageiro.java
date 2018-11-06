package br.com.palves.pbd.model.bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
		return super.toString()+" [tipoAirBag=" + tipoAirBag + ", direcaoAssistida=" + direcaoAssistida
				+ ", cintoSeguancaTraseiro=" + cintoSeguancaTraseiro + ", rodaLigaLeve=" + rodaLigaLeve
				+ ", controlePoluicaoAr=" + controlePoluicaoAr + "]";
	}
	
	
}
