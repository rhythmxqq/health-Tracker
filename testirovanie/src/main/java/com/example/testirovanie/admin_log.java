package com.example.testirovanie;

import animations.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class admin_log {

    @FXML
    private Button enter_button;

    @FXML
    private TextField log_text;

    @FXML
    private PasswordField pas_text;
    @FXML
    private Button backButton;

    String login = "localhost";

    String password = "123qweasd";

    @FXML
    void OnActionEnter(ActionEvent event) {
        if(log_text.getText().equals(login) && pas_text.getText().equals(password)){
            enter_button.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainWindow.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            Shake userLoginAnim = new Shake(log_text);
            Shake userPassAnim = new Shake(pas_text);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }
    @FXML
    void onActionBack(ActionEvent event) {
        enter_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ChoiceMenu.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
