package com.example.testirovanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainMenuUserHealth implements Initializable {

    @FXML
    private Label name_user_qq;

    @FXML
    private Label all_callories_label;

    @FXML
    private Button back_button_usermenu;

    @FXML
    private TableColumn<?, ?> callories_id;

    @FXML
    private Label calories_label_product;
    @FXML
    private Button reset_button;


    @FXML
    private Button eating_button;

    @FXML
    private TableColumn<?, ?> id_grames;

    @FXML
    private TableView<Products> list_products;

    @FXML
    private TableColumn<?, ?> product_id;

    @FXML
    private TextField text_grame_product;
    String name2 = "";

    ObservableList<Products> listM = FXCollections.observableArrayList();

    int index = -1;

    //метод возвращения индекса
    @FXML
    void OnSelectedProducts(MouseEvent event) {
        try{
            int index = list_products.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            text_grame_product.setText(id_grames.getCellData(index).toString());
            calories_label_product.setText(callories_id.getCellData(index).toString());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    public void labelTextAll() throws Exception{
        name2 = name_user_qq.getText();
        String all_cal = "";
        DatabaseHandler db = new DatabaseHandler();
        Connection connection = db.getDbConnection();
        String sqlContent = String.format("select * from users where username='%s'", name2);
        ResultSet rs = connection.createStatement().executeQuery(sqlContent);
        while (rs.next()){
           all_cal += rs.getString(8);
        }
        all_callories_label.setText(all_cal);
    }
    //добавления калорий в счетчик, сам калькулятоо
    public void addAllCallories(){
        if(text_grame_product.getText().equals("")){
            JOptionPane.showMessageDialog(null,"данные  не введены");
        }
        else {
            try {
            name2 = name_user_qq.getText();
            int callories = Integer.parseInt(calories_label_product.getText());
            float grames = Integer.parseInt(text_grame_product.getText());
            float grameskof = (float) (callories / 100 + (callories % 100 * 0.01));
            float resault = grameskof * grames;

                DatabaseHandler db = new DatabaseHandler();
                Connection con = db.getDbConnection();
                Statement statement = con.createStatement();
                int rows = statement.executeUpdate("UPDATE users SET callories_account = callories_account + " + resault + " where username='" + name2 + "'");
            } catch (SQLException e) {
                e.getMessage();
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            }
        }
        }
    //клк на кнопку вызова методов для подсчета калорий и обновления значений
    @FXML
    void eating_product_button(ActionEvent event)  {
        boolean result = text_grame_product.getText().matches("[0-9]*");
        if(result) {
            try {
                addAllCallories();
                labelTextAll();
            }
            catch (IOException ex){
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"данные введены неверно");
        }
    }

    //метод инициализирующий значения при запуске
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name2 = name_user_qq.getText();
        DatabaseHandler db = new DatabaseHandler();
        try {
            Connection con =  db.getDbConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from products");
            product_id.setCellValueFactory(new PropertyValueFactory<>("name_products"));
            callories_id.setCellValueFactory(new PropertyValueFactory<>("callories"));
            id_grames.setCellValueFactory(new PropertyValueFactory<>("grames"));

            while (rs.next()){
                listM.add(new Products(rs.getString("name_products"),rs.getString("callories_product"),rs.getString("grames_product")));
            }

            list_products.setItems(listM);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String all_cal = "";
            Connection connection = db.getDbConnection();
            String sqlContent = String.format("select * from users where username='%s'", name2);
            ResultSet rs = connection.createStatement().executeQuery(sqlContent);
            while (rs.next()){
                all_cal += rs.getString(8);
            }
            all_callories_label.setText(all_cal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //кнопка обнуления калорий
    @FXML
    void click_reset_button(ActionEvent event) throws Exception {
        labelTextAll();
        name2 = name_user_qq.getText();
        try {
            DatabaseHandler db = new DatabaseHandler();
            Connection con = db.getDbConnection();
            Statement statement = con.createStatement();
            int rows = statement.executeUpdate("UPDATE users SET callories_account = 0"+" where username='"+name2+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        labelTextAll();
    }
    //кнопка назад
    public void back_button_active(ActionEvent actionEvent) {
        back_button_usermenu.getScene().getWindow().hide();

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
