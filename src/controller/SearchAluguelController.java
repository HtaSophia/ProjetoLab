package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import dao.AluguelCarroDAO;
import dao.ContratoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;

public class SearchAluguelController {

    AluguelCarroDAO alugueis = new AluguelCarroDAO();
    
	@FXML
	private JFXListView<AluguelCarro> listView;

	@FXML
	private TextField txtSearch;

	@FXML
	void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuAluguelCarro.fxml");
	}

	@FXML
	void btRemove(ActionEvent event) {
		int id = listView.getSelectionModel().getSelectedItem().getId();
		alugueis.removerObj(id);
		listView.getSelectionModel().clearSelection();
	}

	@FXML
	void btSearch(ActionEvent event) {
		int txt = Integer.parseInt(txtSearch.getText());
		listView.getItems().clear();
		
		ArrayList<AluguelCarro> listaCarros = alugueis.getAllObj();
		AluguelCarro op = null;
		for (AluguelCarro aluguelCarro : listaCarros) {
			if(aluguelCarro.getContrato().getHospede().getCpf() == txt) {
				 op = aluguelCarro;
				 break;
			}
		}

		ObservableList<AluguelCarro> contratos = FXCollections.observableArrayList();
        contratos.addAll(op);
        
		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<AluguelCarro> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<AluguelCarro>() {
				@Override
				public String toString(AluguelCarro object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public AluguelCarro fromString(String string) {
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
	public void initialize() {

		ArrayList<AluguelCarro> listaCarros = alugueis.getAllObj();

		ObservableList<AluguelCarro> contratos = FXCollections.observableArrayList();
		contratos.addAll(listaCarros);

		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<AluguelCarro> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<AluguelCarro>() {
				@Override
				public String toString(AluguelCarro object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public AluguelCarro fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}

}
