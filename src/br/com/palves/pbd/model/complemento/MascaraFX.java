package br.com.palves.pbd.model.complemento;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * @author: Rog�rio M. de Queiroz adapted by P Alves
 * */

public abstract class MascaraFX {

	private static List<KeyCode> ignoreKeyCodes = new ArrayList<>();

	public static void ignoreKeys(TextField textField) {
		textField.addEventFilter(KeyEvent.KEY_PRESSED, (EventHandler) new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (ignoreKeyCodes.contains(keyEvent.getCode())) {
					keyEvent.consume();
				}
			}
		});
	}

	//xxxxx-xxxxx-xxxxx-xxxxx
	public static void serialTextField(final TextField textField) {
		MascaraFX.maxField(textField, 23);
		textField.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if (newValue.intValue() < 24) {
				String value = textField.getText();
				value = value.replaceAll("[^\\w]", "");
				value = value.replaceFirst("(\\w{5})(\\w)", "$1-$2");
				value = value.replaceFirst("(\\w{5})\\-(\\w{5})(\\w)", "$1-$2-$3");
				value = value.replaceFirst("(\\w{5})\\-(\\w{5})\\-(\\w{5})(\\w)", "$1-$2-$3-$4");
				textField.setText(value.toUpperCase());
				MascaraFX.positionCaret(textField);
			}
		});
	}

	public static void dateField(final TextField textField) {
		MascaraFX.maxField(textField, 10);
		textField.lengthProperty().addListener((ChangeListener) new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() < 11) {
					String value = textField.getText();
					value = value.replaceAll("[^0-9]", "");
					value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
					value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
					textField.setText(value);
					MascaraFX.positionCaret(textField);
				}
			}
		});
	}

	public static void numericField(final TextField textField) {
		textField.lengthProperty().addListener((ChangeListener) new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				char ch;
				if (newValue.intValue() > oldValue.intValue() && ((ch = textField.getText().charAt(oldValue.intValue())) < '0' || ch > '9')) {
					textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
				}
			}
		});
	}
	public static void letrasField(final TextField textField) {
		textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				String caracteres="ABCDEFGHIJKLMNOPQRSTUVXYZ";
				String caracteres2 = caracteres.toLowerCase();
				if(caracteres.contains(evt.getCharacter()+"") || (caracteres2.contains(evt.getCharacter()+""))){
					String d = evt.getCharacter().toUpperCase();
					int p = textField.getCaretPosition();
					evt.consume();
					textField.setText(textField.getText()+d);
					textField.positionCaret(p+1);
				}else 
					evt.consume();  
			}
		});
	}

	public static void monetaryField(final TextField textField) {
		textField.setAlignment(Pos.CENTER_RIGHT);
		textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceAll("([0-9]{1})([0-9]{14})$", "$1.$2");
			value = value.replaceAll("([0-9]{1})([0-9]{11})$", "$1.$2");
			value = value.replaceAll("([0-9]{1})([0-9]{8})$", "$1.$2");
			value = value.replaceAll("([0-9]{1})([0-9]{5})$", "$1.$2");
			value = value.replaceAll("([0-9]{1})([0-9]{2})$", "$1,$2");
			textField.setText(value);
			MascaraFX.positionCaret(textField);
			textField.textProperty().addListener((ChangeListener) new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
					if (newValue.length() > 17) {
						textField.setText(oldValue);
					}
				}
			});
		}
				);
		textField.focusedProperty().addListener((observableValue, aBoolean, fieldChange) -> {
			int length;
			if (!(fieldChange || (length = textField.getText().length()) <= 0 || length >= 3)) {
				textField.setText(textField.getText() + "00");
			}
		}
				);
	}

	public static BigDecimal monetaryValueFromField(TextField textField) {
		if (textField.getText().isEmpty()) {
			return null;
		}
		BigDecimal retorno = BigDecimal.ZERO;
		NumberFormat nf = NumberFormat.getNumberInstance();
		try {
			Number parsedNumber = nf.parse(textField.getText());
			retorno = new BigDecimal(parsedNumber.toString());
		} catch (ParseException ex) {
			Logger.getLogger( MascaraFX.class.getName()).log(Level.SEVERE, null, ex);
		}
		return retorno;
	}

	public static void cpfCnpjField(TextField textField) {
		MascaraFX.maxField(textField, 18);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			if (number2.intValue() <= 14) {
				value = value.replaceAll("[^0-9]", "");
				value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
				value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
				value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
			} else {
				value = value.replaceAll("[^0-9]", "");
				value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
				value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
				value = value.replaceFirst("(\\d{3})(\\d)", "$1/$2");
				value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
			}
			textField.setText(value);
			MascaraFX.positionCaret(textField);
		}
				);
	}

	public static void cepField(TextField textField) {
		MascaraFX.maxField(textField, 9);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
			textField.setText(value);
			MascaraFX.positionCaret(textField);
		}
				);
	}
	public static void foneField(TextField textField) {
		MascaraFX.maxField(textField, 14);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			try {
				String value = textField.getText();
				value = value.replaceAll("[^0-9]", "");
				int tam = value.length();
				value = value.replaceFirst("(\\d{2})(\\d)", "($1)$2");
				value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
				if (tam > 10) {
					value = value.replaceAll("-", "");
					value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
				}
				textField.setText(value);
				MascaraFX.positionCaret(textField);

			} catch (Exception ex) {
			}
		}
				);
	}

	public static void cpfField(TextField textField) {
		MascaraFX.maxField(textField, 14);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
			try {
				textField.setText(value);
				MascaraFX.positionCaret(textField);
			}catch(Exception ex){}
		}
				);
	}

	public static void cnpjField(TextField textField) {
		MascaraFX.maxField(textField, 18);
		textField.lengthProperty().addListener((observableValue, number, number2) -> {
			String value = textField.getText();
			value = value.replaceAll("[^0-9]", "");
			value = value.replaceFirst("(\\d{2})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
			value = value.replaceFirst("(\\d{3})(\\d)", "$1/$2");
			value = value.replaceFirst("(\\d{4})(\\d)", "$1-$2");
			textField.setText(value);
			MascaraFX.positionCaret(textField);
		}
				);
	}

	private static void positionCaret(TextField textField) {
		Platform.runLater(() -> {
			if (textField.getText().length() != 0) {
				textField.positionCaret(textField.getText().length());
			}
		}
				);
	}

	public static void maxField(TextField textField, Integer length) {
		textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			if (newValue == null || newValue.length() > length) {
				textField.setText(oldValue);
			}
		}
				);
	}

	public static String onlyDigitsValue(TextField field) {
		String result = field.getText();
		if (result == null) {
			return null;
		}
		return result.replaceAll("[^0-9]", "");
	}

	public static String onlyAlfaNumericValue(TextField field) {
		String result = field.getText();
		if (result == null) {
			return null;
		}
		return result.replaceAll("[^0-9]", "");
	}
	static {
		Collections.addAll(ignoreKeyCodes, new KeyCode[]{KeyCode.F1, KeyCode.F2, KeyCode.F3, KeyCode.F4, KeyCode.F5, KeyCode.F6, KeyCode.F7, KeyCode.F8, KeyCode.F9, KeyCode.F10, KeyCode.F11, KeyCode.F12});
	}

	public static void placa(final TextField textField) {
		maxField(textField,7); //Tamanho Maximo
		String caracteres="ABCDEFGHIJKLMNOPQRSTUVXYZ";
		String caracteres2 = caracteres.toLowerCase();
		String num = "1234567890";
		textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(textField.getText().replace(" ","").length()<3) {
					if(caracteres.contains(evt.getCharacter()+"") || (caracteres2.contains(evt.getCharacter()+""))){
						String d = evt.getCharacter().toUpperCase();
						int p = textField.getCaretPosition();
						evt.consume();
						textField.setText(textField.getText().replace(" ","")+d);
						textField.positionCaret(p+1);
					}else 
						evt.consume();  
				}else if(textField.getText().replace(" ","").length()>=3) {
					if(num.contains(evt.getCharacter()+"")){
						String d = evt.getCharacter().toUpperCase();
						int p = textField.getCaretPosition();
						evt.consume();
						textField.setText(textField.getText().replace(" ", "")+d);
						textField.positionCaret(p+1);
					}else 
						evt.consume();  
				}
			}
		});
	}
	public static void plac(TextField textField) {
		maxField(textField,7);
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String antigo, String novo) {
				if (!letrasDaPlacaValida(novo, antigo, textField) || !numerosDaPlacaValida(novo, antigo, textField)) {
					desfazAlteracao(antigo, textField);
				}
			}
		});

	}
	private static boolean letrasDaPlacaValida(String novo, String antigo, TextField textField) {
		for (int i = 0; i < novo.length() && i<3; i++) {
			try {
				System.out.println("N:"+i);
				Integer.parseInt(novo.substring(i, i + 1));
				return false;
			}catch (NumberFormatException e) {	

			}
		}
		return true;
	}

	private static boolean numerosDaPlacaValida(String novo, String antigo, TextField textField) {
		if (novo.length() >= 4 && novo.length() <= 7) {
			try {
				Integer.parseInt(novo.substring(3));
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		} else if (novo.length() < 4) {
			return true;
		}
		return false;
	}

	private static void desfazAlteracao(String antigo, TextField textField) {
		if (antigo != null && !antigo.isEmpty()) {
			textField.setText(antigo);
		} else {
			textField.clear();
		}
	}

}
