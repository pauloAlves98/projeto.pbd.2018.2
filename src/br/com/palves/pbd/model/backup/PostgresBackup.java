/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.palves.pbd.model.backup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.palves.pbd.view.Alerta;
import javafx.application.Platform;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Jorge
 */
public class PostgresBackup {
    
    public PostgresBackup()
    {
      
    }
     public static void realizaBackup(String pgDump,String dir) throws IOException, InterruptedException{      
           final List<String> comandos = new ArrayList<String>();  
           System.out.println(pgDump);
           System.out.println(dir+new Date().toString());
                 //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\9.4\\bin\\pg_dump.exe"); //cecom
                //comandos.add("C:\\Arquivos de programas\\PostgreSQL\\9.2\\bin\\pg_dump.exe"); 
                		comandos.add(pgDump);//"C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump.exe"
                       //comandos.add("-i");      
                       comandos.add("-h");      
                       comandos.add("localhost");
                       //comandos.add("192.168.0.25");
                       comandos.add("-p");      
                       comandos.add("5432");      
                       comandos.add("-U");      
                       comandos.add("postgres");   //User   
                       comandos.add("-F");      
                       comandos.add("c");      
                       comandos.add("-b");      
                       comandos.add("-v");      
                       comandos.add("-f"); 
                       //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+JOptionPane.showInputDialog(null,"Digite o nome do Backup")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
                       //comandos.add("C:\\TesteHib4\\Backups do Banco de Dados\\"+(Character.getNumericValue(recebe)+1)+" "+getDateTime()+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
                       comandos.add(dir+"\\"+new Date().toString().replace(" ", "").replace(":","_")+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
                       comandos.add("banco_pbd_2018_2");      
                       ProcessBuilder pb = new ProcessBuilder(comandos);      
                       pb.environment().put("PGPASSWORD", "123");              
                       try {      
                           final Process process = pb.start();      
                           final BufferedReader r = new BufferedReader(      
                               new InputStreamReader(process.getErrorStream()));      
                           String line = r.readLine();      
                           while (line != null) {      
                           System.err.println(line);      
                           line = r.readLine();      
                           }      
                           r.close();      
                           process.waitFor();    
                           process.destroy(); 
                           Alerta.mostrarAlertaInformacao("backup realizado com sucesso!");  
                       } catch (IOException e) {      
                           e.printStackTrace(); 
                           throw new IOException(e);
                       } catch (InterruptedException ie) {      
                           ie.printStackTrace(); 
                           throw new IOException(ie);
                       } 
     }
     
    public static void main(String args[]) throws IOException, InterruptedException
    {
       
    }
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");
        Date date = new Date();
        return dateFormat.format(date);
    } 

}
