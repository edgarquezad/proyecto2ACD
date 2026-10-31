package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Interfaz extends Application {

    @Override
    public void start(Stage escenario) throws Exception {

        URL recurso = getClass().getResource("/vistas/principal.fxml");

        if (recurso == null) {
            throw new IllegalStateException(
                    "No se encuentra principal.fxml. Comprueba la carpeta resources."
            );
        }

        Parent raiz = FXMLLoader.load(recurso);

        Scene escena = new Scene(raiz);
        escena.getStylesheets().add(
                getClass().getResource("/estilos/estilos.css").toExternalForm()
        );


        escenario.setTitle("Asociación de juegos de mesa");
        escenario.setMinWidth(800);
        escenario.setMinHeight(550);
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
