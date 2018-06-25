package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RestauranteController {

	@FXML
    void btNew(ActionEvent event) throws IOException {
		Main.changeScene("../view/NewRestaurante.fxml");
    }
	
	@FXML
    void btSearch(ActionEvent event) throws IOException {
		Main.changeScene("../view/SearchRestaurante.fxml");
    }

	
	@FXML
    void btBack(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHotel.fxml");
    }
}
