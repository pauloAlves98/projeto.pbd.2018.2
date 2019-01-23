package br.com.palves.pbd.enums;

public enum QueryEnum {
	RESERVACLIENTEDATA("Reserva.listarPorParametroClienteData"),RESERVACLIENTE("Reserva.listarPorParametroCliente"),LISTARVEICULOPORPARAMETROFILIALCATEGORIA("Veiculo.listarPorParametroFilialCategoria"),
	LISTARVEICULOPORPARAMETROFILIALCATEGORIAGERENTE("Veiculo.listarPorParametroFilialCategoriaGerente"),
	RESERVADATA("Reserva.listarPorParametroData");
	private String valor;
	private QueryEnum(String v) {
		valor = v;
	}
	public String getValor() {
		return valor;
	}
}
