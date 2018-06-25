package controller;

import java.io.IOException;

import dao.AluguelCarroDAO;
import dao.BabysitterDAO;
import dao.ContratoDAO;
import dao.HospedeDAO;
import dao.QuartoDAO;
import dao.RestauranteDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Contrato;
import model.Hospede;

public class Main extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
         stage = primaryStage;
        Pane fxmlHotel = FXMLLoader.load(getClass().getResource("../view/MenuHotel.fxml"));
        scene = new Scene(fxmlHotel, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Main.class.getResource(fxml));
        scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
