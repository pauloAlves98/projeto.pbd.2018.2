package br.com.palves.pbd.view;

import javafx.scene.control.Alert;

public class Alerta extends Alert{
	private static Alerta instance;
	private Alerta() {
		super(AlertType.NONE);
	}
	
	public static Alerta getInstance() {
		if(instance == null) {
			instance = new Alerta();
		}
		return instance;
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
