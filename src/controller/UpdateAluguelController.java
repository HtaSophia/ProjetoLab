package controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTimePicker;
import dao.AluguelCarroDAO;
import dao.ContratoDAO;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;

public class UpdateAluguelController {
	
	ContratoDAO contratos = new ContratoDAO();
    AluguelCarroDAO alugueis = new AluguelCarroDAO();
    
    String horaAluguel;
    AluguelCarro contrato;
    int periodo;
    boolean tanque, seguro;

    @FXML
    private JFXListView<AluguelCarro> hospedes;

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
    void btOk(ActionEvent event) {

        if (tmHoraAluguel.getValue() != null && hospedes.getSelectionModel().getSelectedItem() != null && (simples.isSelected() || luxo.isSelected())) {
            AluguelCarro.Tipos tipocarro = null;
            contrato = hospedes.getSelectionModel().getSelectedItem();

            if (simples.isSelected()) {
                tipocarro = AluguelCarro.Tipos.AUTOMOVEL_EXECUTIVO;
            }
            if (luxo.isSelected()) {
                tipocarro = AluguelCarro.Tipos.AUTOMOVEL_LUXO;
            }
            
            contrato.setQuantiaDiarias((int) slPeriodo.getValue());
            contrato.setTanqueCheio(chTanqueCheio.isSelected());
            contrato.setSeguroCarro(tmSeguro.isSelected());
            horaAluguel = tmHoraAluguel.getValue().toString();

            double valor = periodo * tipocarro.getValor();
            if (tanque) {
                valor += AluguelCarro.Tipos.TANQUE_CHEIO.getValor();
            }
            if (seguro) {
                valor += AluguelCarro.Tipos.SEGURO_CARRO.getValor();
            }

            contrato.setValorTotal(valor);
            alugueis.atualizarObj(contrato);
        }

    }

}
