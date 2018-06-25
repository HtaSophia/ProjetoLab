package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.Babysitter;
import model.Contrato;
import model.Hospede;
import model.Quarto;
import model.Restaurante;
import model.Servico;

public class SearchContratoController {
	
	HospedeDAO hospedes = new HospedeDAO();
    QuartoDAO quartos = new QuartoDAO();
    ContratoDAO contratos = new ContratoDAO();
    AluguelCarroDAO alugueis = new AluguelCarroDAO();
    RestauranteDAO pedidos = new RestauranteDAO();
    BabysitterDAO contratosBabysitter = new BabysitterDAO();
    
	ArrayList<Contrato> listaContratos = contratos.getAllObj();
	ObservableList<Contrato> Contratos = FXCollections.observableArrayList();
	double valortotal = 0;

	@FXML
	private JFXListView<Contrato> listView;

	@FXML
	private JFXListView<Servico> listViewFatura;

	@FXML
	private Label valorTotal;

	@FXML
	private TextField txtSearch;

	@FXML
	void rbAbertos(ActionEvent event) {
		listView.getItems().clear();
		Contratos.clear();
		for (Contrato contrato : listaContratos) {
			if (contrato.isStatus()) {
				try {
					Contratos.addAll(contrato);
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}

		listView.setItems(Contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Contrato> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Contrato>() {
				@Override
				public String toString(Contrato object) {
					return object.getHospede().getNome() + "\t" + object.getTotalPagar();
				}

				@Override
				public Contrato fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}

	@FXML
	void rbFechados(ActionEvent event) {
		listView.getItems().clear();
		for (Contrato contrato : listaContratos) {
			if (contrato.isStatus() == false) {
				try {
					Contratos.addAll(contrato);
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}

		listView.setItems(Contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Contrato> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Contrato>() {
				@Override
				public String toString(Contrato object) {
					return object.getHospede().getNome() + "\t" + object.getTotalPagar();
				}

				@Override
				public Contrato fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
		
	}

	@FXML
	void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuContrato.fxml");
	}

	@FXML
	void btRemove(ActionEvent event) {
		int id = listView.getSelectionModel().getSelectedItem().getId();
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		ArrayList<Quarto> Quartos = quartos.getAllObj();
		ArrayList<AluguelCarro> Alugueis = alugueis.getAllObj();
		ArrayList<Babysitter> babysitters = contratosBabysitter.getAllObj();
		ArrayList<Restaurante> Pedidos = pedidos.getAllObj();

		for (Quarto quarto : Quartos) {
			if (quarto.getContrato().getId() == id) {
				servicos.add(quarto);
			}
		}

		for (AluguelCarro aluguel : Alugueis) {
			if (aluguel.getContrato().getId() == id) {
				servicos.add(aluguel);
			}
		}

		for (Babysitter babysitter : babysitters) {
			if (babysitter.getContrato().getId() == id) {
				servicos.add(babysitter);
			}
		}

		for (Restaurante restaurante : Pedidos) {
			if (restaurante.getContrato().getId() == id) {
				servicos.add(restaurante);
			}
		}
		
		ObservableList<Servico> serv = FXCollections.observableArrayList();
		serv.addAll(servicos);

		listViewFatura.setItems(serv);
		listViewFatura.setCellFactory(done -> {
			TextFieldListCell<Servico> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Servico>() {
				@Override
				public String toString(Servico object) {
					valortotal += object.getValorTotal();
					return object.getDescricao();
				}

				@Override
				public Servico fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
		
		valorTotal.setText("R$ " + valortotal);

		contratos.removerObj(id);
	}

	@FXML
	void btSearch(ActionEvent event) {
		int txt = Integer.parseInt(txtSearch.getText());
		listView.getItems().clear();

		ArrayList<Contrato> listaContratos = contratos.getAllObj();
		Contrato op = null;
		for (Contrato Contrato : listaContratos) {
			if (Contrato.getHospede().getCpf() == txt) {
				op = Contrato;
				break;
			}
		}
		Contratos.clear();
		Contratos.addAll(op);

		listView.setItems(Contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Contrato> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Contrato>() {
				@Override
				public String toString(Contrato object) {
					return object.getHospede().getNome() + "\t" + object.getTotalPagar();
				}

				@Override
				public Contrato fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});

	}

	@FXML
	void btUpdate(ActionEvent event) {

	}

	
	void initialize() {
		ArrayList<Contrato> listaContratos = contratos.getAllObj();

		ObservableList<Contrato> Contratos = FXCollections.observableArrayList();
		Contratos.addAll(listaContratos);

		listView.setItems(Contratos);
		listView.setCellFactory(done -> {
			TextFieldListCell<Contrato> cell = new TextFieldListCell<>();
			cell.setConverter(new StringConverter<Contrato>() {
				@Override
				public String toString(Contrato object) {
					return object.getHospede().getNome() + "\t" + object.getTotalPagar() + "\t" + object.getStatus();
				}

				@Override
				public Contrato fromString(String string) {
					throw new UnsupportedOperationException("Not supported yet.");
				}
			});
			return cell;
		});
	}
}
