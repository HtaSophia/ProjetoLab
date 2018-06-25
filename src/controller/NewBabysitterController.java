package controller;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.BabysitterDAO;
import dao.ContratoDAO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.Babysitter;
import model.Contrato;
import model.Hospede;

public class NewBabysitterController {


    BabysitterDAO contratosBabysitter = new BabysitterDAO();
    ContratoDAO contratos = new ContratoDAO();
    
    @FXML
    private JFXTimePicker timeInicial;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXSlider slPeriodo;

    @FXML
    private JFXTimePicker timeFinal;

    @FXML
    private JFXComboBox<Contrato> hospede;

    @FXML
    private JFXSlider slIdade;

    @FXML
    void btCancel(ActionEvent event) throws IOException {
        Main.changeScene("../view/MenuBabysitter.fxml");
    }

    @FXML
    void btOk(ActionEvent event) throws IOException {

        if (timeInicial.getValue() != null && timeFinal.getValue() != null && txtNome.getText() != null) {
            Contrato c = hospede.getValue();
            contratosBabysitter.addObj(new Babysitter(c, (int) slPeriodo.getValue()));
            Main.changeScene("../view/MenuBabysitter.fxml");

        }

    }

    @FXML
    void initialize() {
        ArrayList<Contrato> listacontr = contratos.getAllObj();

        ObservableList<Contrato> contratos = FXCollections.observableArrayList();
        contratos.addAll(listacontr);

        hospede.setItems(contratos);
        hospede.setConverter(
                new StringConverter<Contrato>() {
            @Override
            public String toString(Contrato object) {
                return object.getHospede().getNome() + " - " + object.getHospede().getCpf();

            }

            @Override
            public Contrato fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });

    }
}
