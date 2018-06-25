package controller;

import java.io.IOException;

import dao.ContratoDAO;
import dao.HospedeDAO;
import dao.QuartoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Contrato;
import model.Hospede;

public class ContratoController {
	
	@FXML
    void btNew(ActionEvent event) throws IOException {
		Main.changeScene("../view/NewHospede.fxml");
		System.out.println("Contrato");
    }

	@FXML
    void btSearch(ActionEvent event) throws IOException {
		Main.changeScene("../view/SearchContrato.fxml");
		System.out.println("Contrato");
    }

	@FXML
    void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHospedagem.fxml");
	}
}