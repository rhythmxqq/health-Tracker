package com.example.testirovanie;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class RegController {

    @FXML
    private Button regButton;

    @FXML
    private TextField reg_log;
    @FXML
    private TextField reg_location;

    @FXML
    private CheckBox reg_man_chek;

    @FXML
    private TextField reg_name;

    @FXML
    private TextField reg_pas;

    @FXML
    private TextField reg_surname;

    @FXML
    private CheckBox reg_woman_chek;

    @FXML
    void reg_button_enter(ActionEvent event) {
        boolean result = reg_name.getText().matches("[A-zА-я]*") || reg_surname.getText().matches("[A-zА-я]*") || reg_location.getText().matches("[A-zА-я]*");
        if(reg_name.getText() == "" && reg_surname.getText() == "" && reg_pas.getText() == "" && reg_location.getText() == "" && reg_log.getText() == ""){
            JOptionPane.showMessageDialog(null,"данные не введены");
        }
        else if(result){
            JOptionPane.showMessageDialog(null,"данные введены неправильно");
        }
        else {
            regButton.getScene().getWindow().hide();
            regNewUser();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("meniloging.fxml"));
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


    private void regNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String name = reg_name.getText();
        String surname = reg_surname.getText();
        String log = reg_log.getText();
        String pass = reg_pas.getText();
        String locatiom = reg_location.getText();
        String gender = "";
        if(reg_man_chek.isSelected()){
             gender = "Мужской";
        }
        else {
            gender = "Женский";
        }
        User user = new User(name,surname,log,pass,locatiom,gender);
        dbHandler.signUpUser(user);
        regButton.getScene().getWindow().hide();
    }

}
