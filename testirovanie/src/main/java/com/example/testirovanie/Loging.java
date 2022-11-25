package com.example.testirovanie;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Loging {

    @FXML
    private Button button_reg;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_table;
static String id_user;
    @FXML
    private TextField password_table;
    int counter = 0;
    Connection connection = null;

    @FXML
    void initialize() {

    }
    @FXML
    void button_enter_menu(ActionEvent event) throws SQLException, ClassNotFoundException {
        String loginText = login_table.getText().trim();
        String loginPassword = password_table.getText().trim();

        if(!loginText.equals("") &&  !loginPassword.equals("")){
            loginUser(loginText, loginPassword);
            if(counter>=1){
                enter_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainMenuUser.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Scene scene = new Scene(root,600,400);
                Label label  = (Label) scene.lookup("#name_user_qq");
                label.setText(loginText);
                Label label1 = (Label) scene.lookup("#all_callories_label");
                    String all_cal = "";
                    DatabaseHandler db = new DatabaseHandler();
                    Connection connection = db.getDbConnection();
                    String sqlContent = String.format("select * from users where username='%s'", loginText);
                    ResultSet rs = connection.createStatement().executeQuery(sqlContent);
                    while (rs.next()){
                        all_cal += rs.getString(8);
                    }
                    label1.setText(all_cal);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            Shake userLoginAnim = new Shake(login_table);
            Shake userPassAnim = new Shake(password_table);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
       ResultSet result = dbHandler.getUser(user);

       while(true){
           try {
               if (!result.next()) {
                   break;
               }
           } catch (SQLException e) {
               throw new RuntimeException(e);
           }
           counter++;
       }
       if(counter >= 1);
       else{
           Shake userLoginAnim = new Shake(login_table);
           Shake userPassAnim = new Shake(password_table);
           userLoginAnim.playAnim();
           userPassAnim.playAnim();
       }
    }

    @FXML
    void reg_Action(ActionEvent event){
        enter_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
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
