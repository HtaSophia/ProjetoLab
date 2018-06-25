package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HospedeController {
	
	@FXML
    void btSearchHospede(ActionEvent event) throws IOException {
		Main.changeScene("../view/SearchHospede.fxml");
		System.out.println("Search Hospede");
    }

	
	@FXML
    void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHospedagem.fxml");
    }
}