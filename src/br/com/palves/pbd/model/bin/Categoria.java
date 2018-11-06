package br.com.palves.pbd.model.bin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Categoria {
	@Id
	@SequenceGenerator(name="seq_categoria",sequenceName="seq_categoria_id",allocationSize=1,initialValue=1)
	@GeneratedValue(generator="seq_categoria",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@NotNull(message="Campo Nome Nulo!")   //Validador
	@Column(unique=true, nullable=false, length=100)
	private String nome;
	@Column(name="n_passageiro")
	private int nPassageiro;
	private double valor;
	@Column(name="hora_limpeza")
	private int horaLimpeza;
	@Column(length=100,name="tipo_cambio")
	private String tipoCambio;
	@Column(name="ar_condicionado")
	private boolean arCondicionado;
	private boolean radio;
	private boolean dvd;
	private boolean mp3;
	@Column(name="camera_re")
	private boolean cameraRe;
	
	public Categoria() {}

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

	public int getnPassageiro() {
		return nPassageiro;
	}

	public void setnPassageiro(int nPassageiro) {
		this.nPassageiro = nPassageiro;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getHoraLimpeza() {
		return horaLimpeza;
	}

	public void setHoraLimpeza(int horaLimpeza) {
		this.horaLimpeza = horaLimpeza;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public boolean isArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(boolean arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public boolean isRadio() {
		return radio;
	}

	public void setRadio(boolean radio) {
		this.radio = radio;
	}

	public boolean isDvd() {
		return dvd;
	}

	public void setDvd(boolean dvd) {
		this.dvd = dvd;
	}

	public boolean isMp3() {
		return mp3;
	}

	public void setMp3(boolean mp3) {
		this.mp3 = mp3;
	}

	public boolean isCameraRe() {
		return cameraRe;
	}

	public void setCameraRe(boolean cameraRe) {
		this.cameraRe = cameraRe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (arCondicionado ? 1231 : 1237);
		result = prime * result + (cameraRe ? 1231 : 1237);
		result = prime * result + (dvd ? 1231 : 1237);
		result = prime * result + horaLimpeza;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (mp3 ? 1231 : 1237);
		result = prime * result + nPassageiro;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (radio ? 1231 : 1237);
		result = prime * result + ((tipoCambio == null) ? 0 : tipoCambio.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
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
		Categoria other = (Categoria) obj;
		if (arCondicionado != other.arCondicionado)
			return false;
		if (cameraRe != other.cameraRe)
			return false;
		if (dvd != other.dvd)
			return false;
		if (horaLimpeza != other.horaLimpeza)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mp3 != other.mp3)
			return false;
		if (nPassageiro != other.nPassageiro)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (radio != other.radio)
			return false;
		if (tipoCambio == null) {
			if (other.tipoCambio != null)
				return false;
		} else if (!tipoCambio.equals(other.tipoCambio))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	
	
	
	
	
}
