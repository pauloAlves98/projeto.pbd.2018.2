package br.com.palves.pbd.model.complemento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Horario {
	private ArrayList<String>horarios = new ArrayList<String>();
	public Horario(){
		
		carregarHorarios();
	}
	public ArrayList<String> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<String> horarios) {
		this.horarios = horarios;
	}
	private void carregarHorarios(){
		horarios.clear();
		String hor = "";
		String min = "00";
		for(int i = 0;i<25;i++){
			for(int j = 0;j<2;j++){
				if(i<10){
					hor = "0"+i+":";
				}else
					hor = i+":";
				
				if(j==0){
					min = "00";
				}
				else
					min = "30";
				horarios.add(hor + min);
			}	
		}
	}
	public static void main(String[] args) {
		new Horario();
	}
	public boolean intervaloDeHorario(String horarioMin,String horarioMax,String horario){ 
		Date dMin = cofigurarHora(horarioMin);
		Date dMax = cofigurarHora(horarioMax);
		Date dAgora = cofigurarHora(horario);
		
		System.out.println(dAgora);
		System.out.println(dMin.getTime() +"---"+dMin);
		System.out.println(dMax.getTime()+"----"+dMax);

		if (dAgora.getTime() > dMin.getTime() && dAgora.getTime() < dMax.getTime() ){
			return true;
		}
		return false;
	}
	private Date cofigurarHora(String horario){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String[]hora = horario.split(":");
		
		calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hora[0]));
		calendar.set(Calendar.MINUTE,Integer.parseInt(hora[1]));
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
		
	}
}
