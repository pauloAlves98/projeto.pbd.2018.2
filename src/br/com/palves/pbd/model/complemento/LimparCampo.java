package br.com.palves.pbd.model.complemento;

import java.awt.Component;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jfoenix.controls.JFXDatePicker;
import com.toedter.calendar.JDateChooser;

import br.com.palves.pbd.app.App;
import br.com.palves.pbd.controller.Carregar;
import br.com.palves.pbd.controller.ControllerFXCadastroLocacaoCReserva;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LimparCampo {
	/**
	 * @author P Alves 
	 * */
	public static void limparCamposDialog(Component [] components) {
		Carregar.detalhes = false;
		ControllerFXCadastroLocacaoCReserva.setPermissaoGerente(false);
		
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
		Carregar.detalhes = false;
		ControllerFXCadastroLocacaoCReserva.setPermissaoGerente(false);
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
			else if(c instanceof CheckBox) {
				(( CheckBox) c).setSelected(false);
			}
			else if(c instanceof TextArea) {
				(( TextArea) c).setText("");
			}
		}
		return;
	}
	public static void limparCamposFXTOTAL(List<Node> components) {
		Carregar.detalhes = false;
		ControllerFXCadastroLocacaoCReserva.setPermissaoGerente(false);
		for (Node c: components) {
			if(c instanceof javafx.scene.control.TextField) {
				((TextField) c).setText("");
			}
			else if (c instanceof PasswordField) {
				((PasswordField) c).setText("");
			}
			else if(c instanceof  Pane) {
				limparCamposFXTOTAL(((Pane) c).getChildren());
			}
			else if(c instanceof  ScrollPane) {
				limparCamposFXTOTAL(((ScrollPane) c).getChildrenUnmodifiable());
			}
			else if(c instanceof JFXDatePicker) {
				((JFXDatePicker) c).setValue(null);;
			}
			else if(c instanceof TableView) {
				((TableView) c).setItems(null);
			}
			else if(c instanceof CheckBox) {
				(( CheckBox) c).setSelected(false);
			}
			else if(c instanceof TextArea) {
				(( TextArea) c).setText("");
			}
		}
		return;
	}
	public static void limparLogout() {
		for( Pane p:App.getListaDeTelas()) {
			limparCamposFXTOTAL(p.getChildren());
		}
	}
}
