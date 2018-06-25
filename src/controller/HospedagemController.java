package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HospedagemController {
 
	@FXML
    void btContrato(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuContrato.fxml");
		System.out.println("Contrato");
    }

	@FXML
    void btHospede(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHospede.fxml");
		System.out.println("Hospede");
    }
	
	@FXML
    void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHotel.fxml");
    }
}
