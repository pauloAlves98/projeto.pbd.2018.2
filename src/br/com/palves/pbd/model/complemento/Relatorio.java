package br.com.palves.pbd.model.complemento;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import br.com.palves.pbd.exception.DaoException;
import br.com.palves.pbd.model.dao.LocacaoDao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
public class Relatorio {
private String path; //Caminho base
	
	private static String pathToReportPackage = "relatorios/";// Caminho para o package onde estão armazenados os relatorios Jarper
	//Imprime/gera uma lista de Clientes
	public static  void imprimir(List lista,String caminho,String nome) throws Exception	
	{
		System.out.println("Entri");
		JasperReport report = JasperCompileManager.compileReport(getPathToReportPackage() + nome);
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
		JasperViewer jv = new JasperViewer(print,false);
		jv.setTitle("Vizualização de Relatório!");
		System.out.println("Passei aqui!");
		jv.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jv.addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				jv.dispose();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		jv.setVisible(true);
		
		//JasperExportManager.exportReportToPdfFile(print, "res/Relatorio_de_Clientes.pdf");		
	}
 
	public static  String getPathToReportPackage() {
		return pathToReportPackage;
	}
	public String getPath() {
		return this.path;
	}
	public static void main(String[] args) {
		try {
			List l = LocacaoDao.getInstance().buscarViewPorPeriodoFinanceiro(TratadorDeMascara.converterStringData("10/10/2018"), new Date());
			System.out.println(l);
			imprimir(l,"","relatorioLocacoesPorPeriodoFinanceiro.jrxml");
			System.out.println("Aki");
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Aqui 2");
	}
}
