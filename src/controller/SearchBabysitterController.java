package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import dao.AluguelCarroDAO;
import dao.BabysitterDAO;
import dao.ContratoDAO;
import dao.HospedeDAO;
import dao.QuartoDAO;
import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.Babysitter;

public class SearchBabysitterController {

	BabysitterDAO contratosBabysitter = new BabysitterDAO();
    
	@FXML
	private JFXListView<Babysitter> listView;

	@FXML
	private TextField txtSearch;

	@FXML
	void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuBabysitter.fxml");
	}

	@FXML
	void btRemove(ActionEvent event) {
		int id = listView.getSelectionModel().getSelectedItem().getId();
		contratosBabysitter.removerObj(id);
		listView.getSelectionModel().clearSelection();
	}

	@FXML
	void btSearch(ActionEvent event) {
		int txt = Integer.parseInt(txtSearch.getText());
		listView.getItems().clear();
		
		ArrayList<Babysitter> listaCarros = contratosBabysitter.getAllObj();
		Babysitter op = null;
		for (Babysitter Babysitter : listaCarros) {
			if(Babysitter.getContrato().getHospede().getCpf() == txt) {
				 op = Babysitter;
				 break;
			}
		}

		ObservableList<Babysitter> contratos = FXCollections.observableArrayList();
        contratos.addAll(op);
        
		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Babysitter> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Babysitter>() {
				@Override
				public String toString(Babysitter object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public Babysitter fromString(String string) {
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

		ArrayList<Babysitter> listaBabysitters = contratosBabysitter.getAllObj();

		ObservableList<Babysitter> contratos = FXCollections.observableArrayList();
		contratos.addAll(listaBabysitters);

		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Babysitter> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Babysitter>() {
				@Override
				public String toString(Babysitter object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public Babysitter fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}
}
