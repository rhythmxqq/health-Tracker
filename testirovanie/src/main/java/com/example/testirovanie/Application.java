package com.example.testirovanie;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ChoiceMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Health tracker");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}