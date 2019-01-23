package br.com.palves.pbd.view;





import br.com.palves.pbd.app.App;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class AlertaDetalhes extends Alert{
	private static AlertaDetalhes instance;
	private static BorderPane border;
	private AlertaDetalhes() {
		super(AlertType.WARNING);
		border = new BorderPane();
		border.setPrefSize(900, 600);
		border.setStyle("-fx-background-color:#ffffff;");
		Background n = 	new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
		border.setBackground(n);
		//border.setTop();
		DialogPane d = new DialogPane();
		d.setContent(border);
		d.setPrefSize(900, 400);
		this.getDialogPane().setContent(d);
		this.getDialogPane().setHeader(App.getFechamentoDialog());
		this.getDialogPane().setGraphic(new AnchorPane());
		//this.setResizable(false);
//		this.setOnCloseRequest(new EventHandler<DialogEvent>() {
//			@Override
//			public void handle(DialogEvent arg0) {
//				System.out.println("Eee");
//			}
//		});
	}
	public static AlertaDetalhes getInstance() {
		if(instance == null) {
			instance = new AlertaDetalhes();
		}
		return instance;
	}
	public static BorderPane getBorder() {
		getInstance();
		return border;
	}
}
