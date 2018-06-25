package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * 'MenuHotel.fxml' Controller Class
 */

public class HotelController{
    
	@FXML
	private void btHospedagem(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuHospedagem.fxml");
		System.out.println("Hospedagem");
	}
	
	@FXML
	private void btRestaurante(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuRestaurante.fxml");
		System.out.println("Restaurante");
	}
	
	@FXML
	private void btAluguelCarro(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuAluguelCarro.fxml");
		System.out.println("Aluguel");
	}
	
	@FXML
	private void btBabysitter(ActionEvent event) throws IOException {
		Main.changeScene("../view/MenuBabysitter.fxml");
		System.out.println("Babysitter");
	}

}