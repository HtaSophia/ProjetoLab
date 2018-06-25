package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import dao.HospedeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Hospede;

public class UpdateHospedeController implements Initializable {

    HospedeDAO hospedes = new HospedeDAO();

    @FXML
    private JFXListView<Hospede> listView;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXDatePicker dtDataNascimento;

    @FXML
    private JFXTextField txtEnd;

    @FXML
    private JFXTextField txtcartato;

    Hospede item;

    @FXML
    void btOk(ActionEvent event) throws IOException {
        if (item != null && txtNome.getText() != null && txtEnd.getText() != null && txtcartato.getText() != null && dtDataNascimento.getValue() != null) {

            item.setNome(txtNome.getText());
            item.setEndereco(txtEnd.getText());
            item.setCartaoCredito(Integer.parseInt(txtcartato.getText()));
            item.setDataNascimento(dtDataNascimento.getValue().toString());

            hospedes.atualizarObj(item);
            Main.changeScene("../view/MenuHotel.fxml");
        }
    }

    @FXML
    void btCancelar(ActionEvent event) throws IOException {
        Main.changeScene("../view/MenuHospedagem.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ArrayList<Hospede> listaHospedes = hospedes.getAllObj();

        ObservableList<Hospede> contratos = FXCollections.observableArrayList();
        contratos.addAll(listaHospedes);

        listView.setCellFactory(lv -> {
            TextFieldListCell<Hospede> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Hospede>() {
                @Override
                public String toString(Hospede object) {
                    return "Nome: " + object.getNome() + " - CPF: " + object.getCpf();
                }

                @Override
                public Hospede fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            return cell;
        });

        listView.setOnMouseClicked((MouseEvent event) -> {
            item = listView.getSelectionModel().getSelectedItem();

        });

    }
}
