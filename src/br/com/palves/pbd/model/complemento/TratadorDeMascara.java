package br.com.palves.pbd.model.complemento;

import java.awt.event.KeyAdapter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import  br.com.palves.pbd.exception.ValidacaoException;

public class TratadorDeMascara {
	public static LocalTime dateToLocalTime(Date d) {
		 DateTimeFormatter formatter =
		 DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		        String text = converterDataHoraString(d);
		        LocalDateTime localDateTime = LocalDateTime.parse(text, formatter);
		        LocalTime localTime = localDateTime.toLocalTime();
		        return localTime;
	}
	public static LocalDate dateToLocalDate(Date d) {
		return new Date(d.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public static String valorReais(double valor) {
        String formato = "R$ ##,##0.00";
        DecimalFormat d = new DecimalFormat(formato);
        return d.format(valor);
	}
	public static Date localDatetoDate(LocalDate d) {
		return Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	public static void  mascaraHora(JFormattedTextField hora) {
		try{
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##:##");
			data.install(hora);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static Date unirDataHora(Date data, String hora) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataFormatada = converterDataString(data);
		try {
			return sdf.parse(dataFormatada+" "+hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String converterDataHoraString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		String dataFormatada = converterDataString(data);
//		String horaFormatada = converterHoraString(data);
		String dataF = sdf.format(data);
		return dataF;

	}
	public static String converterDataString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dat = data; // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(dat);
		return dataFormatada;	
	}
	public static Date converterStringData(String dat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			Date d = sdf.parse(dat);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date converterStringDataHora(String dat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date d = sdf.parse(dat);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String converterHoraString(Date horas) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date hora = horas; // Ou qualquer outra forma que tem
		String dataFormatada = sdf.format(hora);
		return dataFormatada;	
	}
	public static Date localTimeToDate(LocalTime localTime) {
	      Calendar calendar = Calendar.getInstance();
	      calendar.clear();
	      //assuming year/month/date information is not important
	      calendar.set(0, 0, 0, localTime.getHour(), localTime.getMinute(), localTime.getSecond());
	      
	      return calendar.getTime();
	}
	public static String localTimetoString(LocalTime t) {
		return converterHoraString(localTimeToDate(t));
	}
	public static Date converterStringHora(String hora) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Date d = sdf.parse(hora);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void aplicarMascaraCPF(JFormattedTextField cpfField) {
		try{
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("###.###.###-##");
			data.install(cpfField);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
//	private void mascaraCep() {
//		try{
//			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("#####-###");
//			format_textField4.install(cepField);
//		}catch (Exception e){}
//	}
	
	public static void aplicarMascaraData(JFormattedTextField dateF) {
		try{
			javax.swing.text.MaskFormatter format_textField4 = new javax.swing.text.MaskFormatter("##/##/####");
			format_textField4.install(dateF);
		}catch (Exception e){}
	}
	
	public static void aplicarMascaraCnpj(JFormattedTextField cnpjField) {
		try{
			javax.swing.text.MaskFormatter data = new javax.swing.text.MaskFormatter("##.###.###/####-##");
			data.install(cnpjField);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static javax.swing.text.MaskFormatter aplicarMascaraCnpj2() {
		javax.swing.text.MaskFormatter data = null;
		try{
			 data = new javax.swing.text.MaskFormatter("##.###.###/####-##");
			return data;
		}catch (Exception e){
			e.printStackTrace();
			return data;
		}
	}
	public static javax.swing.text.MaskFormatter aplicarMascaraCpf2() {
		javax.swing.text.MaskFormatter data = null;
		try{
			 data = new javax.swing.text.MaskFormatter("###.###.###-##");
			return data;
		}catch (Exception e){
			e.printStackTrace();
			return data;
		}
	}
	
	public static Date coletorDeData(String nas) throws ValidacaoException{
		String []data = nas.split("/");
		Calendar c = Calendar.getInstance();
		try {
			int dia = Integer.parseInt(data[0]);
			int mes = Integer.parseInt(data[1]);
			int ano = Integer.parseInt(data[2]);
			c.set(Calendar.DAY_OF_MONTH,dia);
			c.set(Calendar.MONTH,mes-1);
			c.set(Calendar.YEAR,ano);
			System.out.println("Data:"+c.getTime());
			return c.getTime();
		}catch(NumberFormatException e) {
			//lançar validação exception
			throw new ValidacaoException("Erro ao validar data!!!");
		}
		catch(InputMismatchException e) {
			//lançar validação exception
			throw new ValidacaoException("Erro ao validar data!!!");
		}
	}
	/**
	 * @param:CPF sem a mascara*/
	public static boolean isCPF(String CPF) throws ValidacaoException {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11)) {
		    	throw new ValidacaoException("CPF Inválido");
		    }
		 
		    char dig10, dig11;
		    int sm, i, r, num, peso;
		 
		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		// converte o i-esimo caractere do CPF em um numero:
		// por exemplo, transforma o caractere '0' no inteiro 0         
		// (48 eh a posicao de '0' na tabela ASCII)         
		        num = CPF.charAt(i) - 48; 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
		 
		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = CPF.charAt(i) - 48;
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }
		 
		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);
		 
		// Verifica se os digitos calculados conferem com os digitos informados.
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else throw new ValidacaoException("CPF Inválido");
		    } catch (InputMismatchException erro) {
		    	throw new ValidacaoException("CPF Inválido");
		    }
		  }
	public static void validaRg(String rg) throws ValidacaoException {
		if(rg.length() !=9)
			throw new ValidacaoException("RG Inválido");
	}
	public static void soNumero(JTextField j) {
		j.addKeyListener(new KeyAdapter() {
			@Override 
			/**
			 * @author: Créditos DevMedia adaptação P_ALVES
			 * */
			public void keyTyped(java.awt.event.KeyEvent evt) {
				String caracteres="0987654321";
				if(!caracteres.contains(evt.getKeyChar()+"")){
					evt.consume();  		
				}
			}
		});
	}
//	 public static Boolean ValidaCep(string cep)
//     {
//         if (cep.Length == 8)
//         {
//             cep = cep.Substring(0, 5) + "-" + cep.Substring(5, 3);
//             //txt.Text = cep;
//         }
//         return System.Text.RegularExpressions.Regex.IsMatch(cep, ("[0-9]{5}-[0-9]{3}"));
//     }
}
