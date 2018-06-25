package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXListView;

import dao.RestauranteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.Restaurante;

public class SearchRestauranteController {
	
	RestauranteDAO pedidos = new RestauranteDAO();
	
	@FXML
	private JFXListView<Restaurante> listView;

	@FXML
	private TextField txtSearch;

	@FXML
	void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuRestaurante.fxml");
	}

	@FXML
	void btRemove(ActionEvent event) {
		int id = listView.getSelectionModel().getSelectedItem().getId();
		pedidos.removerObj(id);
		listView.getSelectionModel().clearSelection();
	}

	@FXML
	void btSearch(ActionEvent event) {
		int txt = Integer.parseInt(txtSearch.getText());
		listView.getItems().clear();
		
		ArrayList<Restaurante> listaCarros = pedidos.getAllObj();
		Restaurante op = null;
		for (Restaurante Restaurante : listaCarros) {
			if(Restaurante.getContrato().getHospede().getCpf() == txt) {
				 op = Restaurante;
				 break;
			}
		}

		ObservableList<Restaurante> contratos = FXCollections.observableArrayList();
        contratos.addAll(op);
        
		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Restaurante> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Restaurante>() {
				@Override
				public String toString(Restaurante object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public Restaurante fromString(String string) {
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

		ArrayList<Restaurante> listapedidos = pedidos.getAllObj();

		ObservableList<Restaurante> contratos = FXCollections.observableArrayList();
		contratos.addAll(listapedidos);

		listView.setItems(contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Restaurante> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Restaurante>() {
				@Override
				public String toString(Restaurante object) {
					return object.getContrato().getHospede().getNome() + "\t" + object.getValorTotal();
				}

				@Override
				public Restaurante fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}
}
