package controller;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.AluguelCarroDAO;
import dao.ContratoDAO;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.AluguelCarro.Tipos;
import model.Contrato;
import model.Hospede;
import utils.MaskFieldUtil;

public class NewAluguelController {

	ContratoDAO contratos = new ContratoDAO();
	AluguelCarroDAO alugueis = new AluguelCarroDAO();
    String horaAluguel;
    Contrato contrato;
    int periodo;
    boolean tanque, seguro;

    @FXML
    private JFXComboBox<Contrato> hospedes;

    @FXML
    private JFXSlider slPeriodo;

    @FXML
    private JFXCheckBox chTanqueCheio;

    @FXML
    private JFXCheckBox tmSeguro;

    @FXML
    private JFXRadioButton luxo;

    @FXML
    private ToggleGroup one;

    @FXML
    private JFXRadioButton simples;

    @FXML
    private JFXTimePicker tmHoraAluguel;

    @FXML
    void btCancel(ActionEvent event) throws IOException {
        Main.changeScene("../view/MenuAluguelCarro.fxml");
    }

    @FXML
    void btOk(ActionEvent event) throws IOException {

        if (tmHoraAluguel.getValue() != null && hospedes.getSelectionModel().getSelectedItem() != null && (simples.isSelected() || luxo.isSelected())) {
            Tipos tipocarro = null;

            if (simples.isSelected()) {
                tipocarro = Tipos.AUTOMOVEL_EXECUTIVO;
            }
            if (luxo.isSelected()) {
                tipocarro = Tipos.AUTOMOVEL_LUXO;
            }
            System.out.println("Passou");
            periodo = (int) slPeriodo.getValue();
            contrato = hospedes.getSelectionModel().getSelectedItem();
            tanque = chTanqueCheio.isSelected();
            seguro = tmSeguro.isSelected();
            horaAluguel = tmHoraAluguel.getValue().toString();

            double valor = periodo * tipocarro.getValor();
            if (tanque) {
                valor += Tipos.TANQUE_CHEIO.getValor();
            }
            if (seguro) {
                valor += Tipos.SEGURO_CARRO.getValor();
            }

            alugueis.addObj(new AluguelCarro(valor, contrato, periodo, tanque, seguro));
            Main.changeScene("../view/MenuAluguelCarro.fxml");

        }

    }

    @FXML
    void initialize() {

        ArrayList<Contrato> listacontr = contratos.getAllObj();

        ObservableList<Contrato> contratos = FXCollections.observableArrayList();
        contratos.addAll(listacontr);

        hospedes.setItems(contratos);
        hospedes.setConverter(
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
