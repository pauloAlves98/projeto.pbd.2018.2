package br.com.palves.pbd.model.bin.views;

import java.util.Date;

public class ViewRevisao {
	private int cod,codVeiculo; 
	private Date horaRevisao;//data e hora!
	private String status,nomeVeiculo,placa,chassi;
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getCodVeiculo() {
		return codVeiculo;
	}
	public void setCodVeiculo(int codVeiculo) {
		this.codVeiculo = codVeiculo;
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
	public String getNomeVeiculo() {
		return nomeVeiculo;
	}
	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	@Override
	public String toString() {
		return "ViewRevisao [cod=" + cod + ", codVeiculo=" + codVeiculo + ", horaRevisao=" + horaRevisao + ", status="
				+ status + ", nomeVeiculo=" + nomeVeiculo + ", placa=" + placa + ", chassi=" + chassi + "]";
	}
	
}
