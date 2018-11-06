package br.com.palves.pbd.model.bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="categoria_carga")
@PrimaryKeyJoinColumn(name="id")
public class CategoriaCarga extends Categoria{
	@Column(name="capacidade_carga")
	private int capacidadeCarga;
	@Column(name="potencia_motor")
	private int potenciaMotor;
	@Column(name="volume_combustivel")
	private int volumeCombustivel;
	@Column(name="distancia_eixo")
	private int distanciaEixo;
	@Column(name="tipo_embreagem",length=50)
	private String tipoEmbreagem;
	@Column(name="consumo_km",length=30)
	private String consumoKm;
	
	public CategoriaCarga() {}

	public int getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(int capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}

	public int getPotenciaMotor() {
		return potenciaMotor;
	}

	public void setPotenciaMotor(int potenciaMotor) {
		this.potenciaMotor = potenciaMotor;
	}

	public int getVolumeCombustivel() {
		return volumeCombustivel;
	}

	public void setVolumeCombustivel(int volumeCombustivel) {
		this.volumeCombustivel = volumeCombustivel;
	}

	public int getDistanciaEixo() {
		return distanciaEixo;
	}

	public void setDistanciaEixo(int distanciaEixo) {
		this.distanciaEixo = distanciaEixo;
	}

	public String getTipoEmbreagem() {
		return tipoEmbreagem;
	}

	public void setTipoEmbreagem(String tipoEmbreagem) {
		this.tipoEmbreagem = tipoEmbreagem;
	}

	public String getConsumoKm() {
		return consumoKm;
	}

	public void setConsumoKm(String consumoKm) {
		this.consumoKm = consumoKm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + capacidadeCarga;
		result = prime * result + ((consumoKm == null) ? 0 : consumoKm.hashCode());
		result = prime * result + distanciaEixo;
		result = prime * result + potenciaMotor;
		result = prime * result + ((tipoEmbreagem == null) ? 0 : tipoEmbreagem.hashCode());
		result = prime * result + volumeCombustivel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaCarga other = (CategoriaCarga) obj;
		if (capacidadeCarga != other.capacidadeCarga)
			return false;
		if (consumoKm == null) {
			if (other.consumoKm != null)
				return false;
		} else if (!consumoKm.equals(other.consumoKm))
			return false;
		if (distanciaEixo != other.distanciaEixo)
			return false;
		if (potenciaMotor != other.potenciaMotor)
			return false;
		if (tipoEmbreagem == null) {
			if (other.tipoEmbreagem != null)
				return false;
		} else if (!tipoEmbreagem.equals(other.tipoEmbreagem))
			return false;
		if (volumeCombustivel != other.volumeCombustivel)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CategoriaCarga [capacidadeCarga=" + capacidadeCarga + ", potenciaMotor=" + potenciaMotor
				+ ", volumeCombustivel=" + volumeCombustivel + ", distanciaEixo=" + distanciaEixo + ", tipoEmbreagem="
				+ tipoEmbreagem + ", consumoKm=" + consumoKm + "]";
	}
}
