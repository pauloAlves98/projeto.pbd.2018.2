package br.com.palves.pbd.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ControllerFXCadastroLocacaoCReserva implements Initializable{

    @FXML
    private BorderPane borderGeral;

    @FXML
    private BorderPane locacaoPane;

    @FXML
    private JFXButton salvarButton;

    @FXML
    private TableView<?> tableFiilial;

    @FXML
    private TableColumn<?, ?> codFilialColumn;

    @FXML
    private TableColumn<?, ?> nomeFilialColumn;

    @FXML
    private TextField filialEntregaField;

    @FXML
    private Button buscarFilialButton;

    @FXML
    private TextField buscaVeiculoField;

    @FXML
    private Button buscarVeiculoButton;

    @FXML
    private TableView<?> tableVeiculo;

    @FXML
    private TableColumn<?, ?> codVeiculoColumn;

    @FXML
    private TableColumn<?, ?> nomeVeiculoColumn;

    @FXML
    private TableColumn<?, ?> situacaoVeiculoColumn;

    @FXML
    private TextField cpfCnpjField;

    @FXML
    private JFXDatePicker dataReserva;

    @FXML
    private TableView<?> tableReserva;

    @FXML
    private TableColumn<?, ?> codReservaColumn;

    @FXML
    private TableColumn<?, ?> situacaoReservaColumn;

    @FXML
    private Button buscarReservaButton;

    @FXML
    private JFXDatePicker dataEntrega;

    @FXML
    private JFXTimePicker horaEntrega;

    @FXML
    private Button infoFilialButton;

    @FXML
    private Label nomeFilialRetirada;

    @FXML
    private RadioButton kmLivre;

    @FXML
    private RadioButton kmControle;

    @FXML
    private Button buscarInfoButton;

    @FXML
    private Button buscarFilialButton211;

    @FXML
    private Button outroVeiculoButton;

    @FXML
    private TableView<?> tableMotorista;

    @FXML
    private TableColumn<?, ?> codMotoristaColumn;

    @FXML
    private TableColumn<?, ?> nomeMotoristaColumn;

    @FXML
    private TableColumn<?, ?> cpfMotoristaColumn;

    @FXML
    private Button buscarMotoristaButton;

    @FXML
    private TextField buscaCategoriaField1;

    @FXML
    private Button cadastroMotoristaButton;

    @FXML
    private TextField precoLocacaoField;

    @FXML
    private TextField valorVeiculoField;

    @FXML
    private TextField valorDiaria;

    @FXML
    private TextField entradaField;

    @FXML
    void buscarFilial(ActionEvent event) {

    }

    @FXML
    void buscarMotorista(ActionEvent event) {

    }

    @FXML
    void buscarOutroVeiculo(ActionEvent event) {

    }

    @FXML
    void buscarVeiculo(ActionEvent event) {

    }

    @FXML
    void cadastrarMotorista(ActionEvent event) {

    }

    @FXML
    void mostrarFilial(ActionEvent event) {

    }

    @FXML
    void mostrarVeiculo(ActionEvent event) {

    }

    @FXML
    void mudancaDeFilialClick(MouseEvent event) {

    }

    @FXML
    void mudancaDeMotoristaClick(MouseEvent event) {

    }

    @FXML
    void mudarFilialKey(KeyEvent event) {

    }

    @FXML
    void mudarMotoristalKey(KeyEvent event) {

    }

    @FXML
    void mudarVeiculoClick(MouseEvent event) {

    }

    @FXML
    void mudarVeiculoKey(KeyEvent event) {

    }

    @FXML
    void salvarLocacao(ActionEvent event) {

    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}