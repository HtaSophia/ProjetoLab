/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dao.AluguelCarroDAO;
import dao.ContratoDAO;
import dao.RestauranteDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.Contrato;
import model.Restaurante;

public class UpdateRestauranteController {

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
    private JFXComboBox<String> txtPedido;

    @FXML
    private JFXTextField txtQuantia;

    @FXML
    private JFXListView<Restaurante> hospedes;

    @FXML
    void btCancel(ActionEvent event) throws IOException {
        Main.changeScene("../view/MenuRestaurante.fxml");
    }

    @FXML
    void btOk(ActionEvent event) {
        if ((lanche.isSelected() || drink.isSelected() || self.isSelected() || buffet.isSelected()) && txtPedido.getValue() != null && txtValor.getText() != null && txtQuantia.getText() != null) {

            String tipo = txtPedido.getValue();

            double valor = Double.parseDouble(txtValor.getText());
            int quantia = Integer.parseInt(txtQuantia.getText());
            RestauranteDAO rdao = new RestauranteDAO();
            Restaurante r = hospedes.getSelectionModel().getSelectedItem();
            r.setValorTotal(quantia * valor);
            r.setPedido(tipo);
            rdao.atualizarObj(r);
            System.out.println(tipo);
        }
    }

    @FXML
    void initialize() {

        txtPedido.setItems(FXCollections.observableArrayList("Lanche", "Self-Service", "Buffet � la carte", "Drinks"));
        ArrayList<Restaurante> listacontr = new RestauranteDAO().getAllObj();

        ObservableList<Restaurante> contratos = FXCollections.observableArrayList();
        contratos.addAll(listacontr);

        hospedes.setItems(contratos);
        hospedes.setCellFactory(lv -> {
            TextFieldListCell<Restaurante> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Restaurante>() {
                @Override
                public String toString(Restaurante object) {
                    return object.getContrato().getStatus() + " - " + object.getContrato().getHospede().getNome() + " - CPF: " + object.getContrato().getHospede().getCpf();
                }

                @Override
                public Restaurante fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            return cell;
        });

    }

}
