package br.com.palves.pbd.model.complemento;

import java.awt.Component;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

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

}
