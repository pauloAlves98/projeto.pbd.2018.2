package br.com.palves.pbd.model.bin.views;

import java.util.Date;

public class ViewLocacao {
	private  int cod,codCliente,qtd;
	private  Date dataPrevistaEntrega, dataRealEntrega,dataRetirada;
	private double precoFinal,taxaCombustivel,taxaHigiene,valorDiaria;
	String situacao,nomeMotorista,cpf,nomeCliente,nomeVeiculo;
	
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public int getCod() {
		return cod;
	}
	
	public String getNomeVeiculo() {
		return nomeVeiculo;
	}

	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}
	public Date getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}
	public void setDataPrevistaEntrega(Date dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}
	public Date getDataRealEntrega() {
		return dataRealEntrega;
	}
	public void setDataRealEntrega(Date dataRealEntrega) {
		this.dataRealEntrega = dataRealEntrega;
	}
	public Date getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public double getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(double precoFinal) {
		this.precoFinal = precoFinal;
	}
	public double getTaxaCombustivel() {
		return taxaCombustivel;
	}
	public void setTaxaCombustivel(double taxaCombustivel) {
		this.taxaCombustivel = taxaCombustivel;
	}
	public double getTaxaHigiene() {
		return taxaHigiene;
	}
	public void setTaxaHigiene(double taxaHigiene) {
		this.taxaHigiene = taxaHigiene;
	}
	public double getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	@Override
	public String toString() {
		return "ViewLocacao [cod=" + cod + ", codCliente=" + codCliente + ", qtd=" + qtd + ", dataPrevistaEntrega="
				+ dataPrevistaEntrega + ", dataRealEntrega=" + dataRealEntrega + ", dataRetirada=" + dataRetirada
				+ ", precoFinal=" + precoFinal + ", taxaCombustivel=" + taxaCombustivel + ", taxaHigiene=" + taxaHigiene
				+ ", valorDiaria=" + valorDiaria + ", situacao=" + situacao + ", nomeMotorista=" + nomeMotorista
				+ ", cpf=" + cpf + ", nomeCliente=" + nomeCliente + ", nomeVeiculo=" + nomeVeiculo + "]";
	}
	
}
