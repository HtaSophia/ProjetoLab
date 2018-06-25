package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AluguelCarroController {

	@FXML
    void btNew(ActionEvent event) throws IOException {
		Main.changeScene("../view/NewAluguelCarro.fxml");
		System.out.println("Contrato");
    }

	@FXML
    void btSearch(ActionEvent event) throws IOException {
		Main.changeScene("../view/SearchAluguelCarro.fxml");
		System.out.println("Contrato");
    }


	@FXML
    void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHotel.fxml");
    }
}