package br.com.palves.pbd.model.bin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Log {
	@Id
	@SequenceGenerator(name="seq_log",sequenceName="seq_log_id",initialValue= 1,allocationSize=1)
	@GeneratedValue(generator="seq_log",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	private String usuario;
	@Column(name="registro_antigo")
	private String registroAntigo;
	@Column(name="tipo_alteracao", length=30)
	private String tipoAlteracao;
	public Log() {
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getRegistroAntigo() {
		return registroAntigo;
	}
	public void setRegistroAntigo(String registroAntigo) {
		this.registroAntigo = registroAntigo;
	}
	public String getTipoAlteracao() {
		return tipoAlteracao;
	}
	public void setTipoAlteracao(String tipoAlteracao) {
		this.tipoAlteracao = tipoAlteracao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((registroAntigo == null) ? 0 : registroAntigo.hashCode());
		result = prime * result + ((tipoAlteracao == null) ? 0 : tipoAlteracao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Log other = (Log) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (registroAntigo == null) {
			if (other.registroAntigo != null)
				return false;
		} else if (!registroAntigo.equals(other.registroAntigo))
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
		return true;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", data=" + data + ", usuario=" + usuario + ", registroAntigo=" + registroAntigo
				+ ", tipoAlteracao=" + tipoAlteracao + "]";
	}
	
}
