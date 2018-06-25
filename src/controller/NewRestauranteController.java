package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;

import com.jfoenix.controls.JFXTextField;
import dao.ContratoDAO;
import dao.RestauranteDAO;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.Contrato;
import model.Hospede;
import model.Restaurante;

public class NewRestauranteController {

	ContratoDAO contratos = new ContratoDAO();
	RestauranteDAO pedidos = new RestauranteDAO();
	
	@FXML
	private JFXRadioButton lanche;

	@FXML
	private ToggleGroup opcoes;

	@FXML
	private JFXRadioButton buffet;

	@FXML
	private JFXRadioButton self;

	@FXML
	private JFXRadioButton drink;

	@FXML
	private JFXTextField txtValor;

	@FXML
	private JFXTextField txtPedido;

	@FXML
	private JFXTextField txtQuantia;

	@FXML
	private JFXComboBox<Contrato> hospede;

	@FXML
	void btCancel(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuRestaurante.fxml");
	}

	@FXML
	void btOk(ActionEvent event) throws IOException {
		if ((lanche.isSelected() || drink.isSelected() || self.isSelected() || buffet.isSelected())
				&& txtPedido.getText() != null && txtValor.getText() != null && txtQuantia.getText() != null) {

			String tipo = txtPedido.getText();

			double valor = Double.parseDouble(txtValor.getText());
			int quantia = Integer.parseInt(txtQuantia.getText());
			pedidos.addObj(new Restaurante(quantia * valor, hospede.getSelectionModel().getSelectedItem(),
					Calendar.getInstance().getTime().toString(), tipo));
			System.out.println(tipo);
			Main.changeScene("../view/MenuRestaurante.fxml");
		}
	}

	@FXML
	void initialize() {

		ObservableList<Contrato> Contratos = FXCollections.observableArrayList();
		Contratos.addAll(contratos.getAllObj());

		hospede.setItems(Contratos);
		hospede.setConverter(new StringConverter<Contrato>() {
			@Override
			public String toString(Contrato object) {
				return object.getHospede().getNome() + " - " + object.getHospede().getCpf();

			}

			@Override
			public Contrato fromString(String string) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});

	}

}
