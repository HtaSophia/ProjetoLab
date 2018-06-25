package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import dao.AluguelCarroDAO;
import dao.BabysitterDAO;
import dao.ContratoDAO;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.AluguelCarro;
import model.Babysitter;
import model.Contrato;

public class UpdateBabysitterController {

     @FXML
    private JFXTimePicker timeInicial;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXSlider slPeriodo;

    @FXML
    private JFXTimePicker timeFinal;

    @FXML
    private JFXListView<Babysitter> hospedes;

    @FXML
    private JFXSlider slIdade;

    @FXML
    void btCancel(ActionEvent event) throws IOException {
    	Main.changeScene("../view/MenuBabysitter.fxml");
    }

    @FXML
    void btOk(ActionEvent event) {

        if(timeInicial.getValue() != null && timeFinal.getValue() != null && txtNome.getText() != null){
            BabysitterDAO bdao = new BabysitterDAO();
            Babysitter c = hospedes.getSelectionModel().getSelectedItem();
            c.setHoraExtra((int) slPeriodo.getValue());
            bdao.atualizarObj(c);
            
        }
        
    }

      @FXML
    void initialize() {

        ArrayList<Babysitter> listacontr = new BabysitterDAO().getAllObj();

        ObservableList<Babysitter> contratos = FXCollections.observableArrayList();
        contratos.addAll(listacontr);

        hospedes.setItems(contratos);
        hospedes.setCellFactory(lv -> {
            TextFieldListCell<Babysitter> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<Babysitter>() {
                @Override
                public String toString(Babysitter object) {
                    return object.getContrato().getStatus() + " - " + object.getContrato().getHospede().getNome() + " - CPF: " + object.getContrato().getHospede().getCpf();
                }

                @Override
                public Babysitter fromString(String string) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            return cell;
        });

    }
}
