package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import dao.ContratoDAO;
import dao.HospedeDAO;
import dao.QuartoDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.Contrato;
import model.Hospede;
import model.Quarto;
import utils.MaskFieldUtil;

public class NewContratoController {

	ContratoDAO contratos = new ContratoDAO();
	HospedeDAO hospedes = new HospedeDAO();
	QuartoDAO quartos = new QuartoDAO();
	
	Hospede hospede;
	String nome, endereco, dataNascimento;
	int cartao;
	int cpf;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextField txtCpf;

	@FXML
	private JFXTextField txtEndereco;

	@FXML
	private JFXTextField txtCartao;

	@FXML
	private DatePicker dpDataNascimento;

	@FXML
	private JFXSlider slPeriodo;

	@FXML
	private JFXSlider slPresidencial;

	@FXML
	private JFXSlider slLuxoTriplo;

	@FXML
	private JFXSlider slLuxoDuplo;

	@FXML
	private JFXSlider slLuxoSimples;

	@FXML
	private JFXSlider slExecutivo;

	@FXML
	private JFXSlider slExecutivoDuplo;

	@FXML
	private JFXSlider slExecutivoSimples;

	@FXML
	private JFXComboBox<Label> cbQuartos;

	@FXML
	private JFXCheckBox chbTarifacao;

	@FXML
	private DatePicker dpDataEntrada;

	@FXML
	void btNext1(ActionEvent event) throws IOException {
		if (txtNome.getText() != null && txtCpf.getText() != null && txtEndereco.getText() != null
				&& txtCartao.getText() != null && dpDataNascimento.getValue() != null) {

			nome = txtNome.getText();
			cpf = Integer.parseInt(txtCpf.getText());
			endereco = txtEndereco.getText();
			cartao = Integer.parseInt(txtCartao.getText());
			dataNascimento = dpDataNascimento.getValue().toString();

			hospede = new Hospede(cpf, nome, dataNascimento, endereco, cartao);
			hospedes.addObj(hospede);
			Main.changeScene("../view/NewContrato.fxml");
		}
	}

	@FXML
	void btBack1(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuContrato.fxml");
	}

	@FXML
	void btNext2(ActionEvent event) throws IOException {
		if (dpDataEntrada.getValue() != null) {

			String dataEntrada = dpDataEntrada.getValue().toString();
			int periodo = (int) slPeriodo.getValue();
			boolean tarifacao = chbTarifacao.isSelected();
			System.out.println(dataEntrada);

			contratos.addObj(new Contrato(hospede, dataEntrada, periodo, tarifacao, true, 0.0));
			Main.changeScene("../view/MenuContrato.fxml");
		}
	}

	@FXML
	void btBack2(ActionEvent event) throws IOException {
		Main.changeScene("../view/NewHospede.fxml");
	}

	@FXML
	void initialize() {

		if (location.getPath().equals(this.getClass().getResource("../view/NewContrato.fxml").getPath())) {

			ArrayList<Quarto> listaQuartos = quartos.getAllObj();
			int contP = 0, contLT = 0, contLD = 0, contLS = 0, contET = 0, contED = 0, contES = 0;
			System.out.println(listaQuartos.get(0).getTipoQuarto().toString());

			for (Quarto quarto : listaQuartos) {
				switch (quarto.getTipoQuarto().toString()) {
				case "PRESIDENCIAL":
					contP++;
					break;
				case "LUXO_TRIPLO":
					contLT++;
					break;
				case "LUXO_DUPLO":
					contLD++;
					break;
				case "LUXO_SIMPLES":
					contLS++;
					break;
				case "EXECUTIVO_TRIPLO":
					contET++;
				case "EXECUTIVO_DUPLO":
					contED++;
				case "EXECUTIVO_SIMPLES":
					contES++;
					break;
				default:
					break;
				}
			}

			slPresidencial.setMax(contP);

			slExecutivo.setMax(contET);
			slExecutivoDuplo.setMax(contED);
			slExecutivoSimples.setMax(contES);

			slLuxoSimples.setMax(contLS);
			slLuxoDuplo.setMax(contLD);
			slLuxoTriplo.setMax(contLT);

		} else if (location.getPath().equals(this.getClass().getResource("../view/NewHospede.fxml").getPath())) {
			MaskFieldUtil.numericField(txtCartao);
			MaskFieldUtil.onlyDigitsValue(txtNome);

		}
	}

}
