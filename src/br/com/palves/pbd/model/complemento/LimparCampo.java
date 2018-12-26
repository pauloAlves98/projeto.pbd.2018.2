package br.com.palves.pbd.model.complemento;

import java.awt.Component;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jfoenix.controls.JFXDatePicker;
import com.toedter.calendar.JDateChooser;

import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LimparCampo {
	/**
	 * @author P Alves 
	 * */
	public static void limparCamposDialog(Component [] components) {
		for (Component c: components) {
			if(c instanceof JTextField) {
				((JTextField) c).setText("");
			}
			else if (c instanceof JFormattedTextField) {
				((JFormattedTextField) c).setText("");
			}
			else if(c instanceof  JPanel) {
				limparCamposDialog(((JPanel) c).getComponents());
			}
			else if(c instanceof JDateChooser) {
				((JDateChooser) c).setDate(null);
			}
		}
		return;
	}
	public static void limparCamposFX(List<Node> components) {
		for (Node c: components) {
			if(c instanceof javafx.scene.control.TextField) {
				((TextField) c).setText("");
			}
			else if (c instanceof PasswordField) {
				((PasswordField) c).setText("");
			}
			else if(c instanceof  Pane) {
				limparCamposFX(((Pane) c).getChildren());
			}
			else if(c instanceof JFXDatePicker) {
				((JFXDatePicker) c).setValue(null);;
			}
		}
		return;
	}

}
