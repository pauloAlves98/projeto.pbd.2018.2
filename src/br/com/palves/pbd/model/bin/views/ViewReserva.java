package br.com.palves.pbd.model.bin.views;

import java.util.Date;

public class ViewReserva {
	private Date dataReserva;
	private String cliente;
	private int codCliente;
	private String categoria;
	private String filial;//FilialRetirada
	private String situacao;
	public ViewReserva(Date dataReserva, String cliente, int codCliente, String categoria, String filial,
			String situacao) {
		super();
		this.dataReserva = dataReserva;
		this.cliente = cliente;
		this.codCliente = codCliente;
		this.categoria = categoria;
		this.filial = filial;
		this.situacao = situacao;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "ViewReserva [dataReserva=" + dataReserva + ", nomeCliente=" + cliente + ", codCliente=" + codCliente
				+ ", nomeCategoria=" + categoria + ", nomeFilialRetirada=" + filial + ", situacao="
				+ situacao + "]";
	}
	
}
