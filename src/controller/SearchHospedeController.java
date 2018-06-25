package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import dao.HospedeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.Hospede;

public class SearchHospedeController {

	HospedeDAO hospedes = new HospedeDAO();
	
	@FXML
	private JFXListView<Hospede> listView;

	@FXML
	private TextField txtSearch;

	@FXML
	void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHospede.fxml");
	}

	@FXML
	void btRemove(ActionEvent event) {
		int cpf = listView.getSelectionModel().getSelectedItem().getCpf();
		hospedes.removerObj(cpf);
		listView.getSelectionModel().clearSelection();
	}

	@FXML
	void btSearch(ActionEvent event) {
		int txt = Integer.parseInt(txtSearch.getText());
		listView.getItems().clear();
		
		ArrayList<Hospede> listaCarros = hospedes.getAllObj();
		Hospede op = null;
		for (Hospede Hospede : listaCarros) {
			if(Hospede.getCpf() == txt) {
				 op = Hospede;
				 break;
			}
		}

		ObservableList<Hospede> contratos = FXCollections.observableArrayList();
        contratos.addAll(op);
        
		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Hospede> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Hospede>() {
				@Override
				public String toString(Hospede object) {
					return object.getNome() + "\t" + object.getCpf() + "\t" + object.getDataNascimento();
				}

				@Override
				public Hospede fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
		
	}

	@FXML
	void btUpdate(ActionEvent event) {

	}

	@FXML
	void initialize() {

		ArrayList<Hospede> listaHospedes = hospedes.getAllObj();

		ObservableList<Hospede> contratos = FXCollections.observableArrayList();
		contratos.addAll(listaHospedes);

		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Hospede> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Hospede>() {
				@Override
				public String toString(Hospede object) {
					return object.getNome() + "\t\t\t\t\t\t" + object.getCpf() + "\t\t\t\t\t" + object.getDataNascimento();
				}

				@Override
				public Hospede fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}
}