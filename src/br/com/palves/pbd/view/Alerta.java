package br.com.palves.pbd.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta extends Alert{
	private static Alerta instance;
	private Alerta() {
		super(AlertType.NONE);
		this.setResizable(true);
	}
	
	public static Alerta getInstance() {
		if(instance == null) {
			instance = new Alerta();
		}
		return instance;
	}
	public static void mostrarAlertaInformacao(String msg) {
		getInstance();
		Alerta a = Alerta.getInstance();
		a.setAlertType(AlertType.INFORMATION);
		a.setMensagem(msg);
		a.show();
	}
	public static void mostrarAlertaErro(String msg) {
		getInstance();
		Alerta a = Alerta.getInstance();
		a.setAlertType(AlertType.ERROR);
		a.setMensagem(msg);
		a.show();
	}
	public static void setType(AlertType type) {
		getInstance();
		instance.setAlertType(type);
	}
	public static void setMensagem(String msg) {
		getInstance();
		instance.setContentText(msg);
	}
	
	
	
	
	
}
