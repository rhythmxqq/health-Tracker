package com.example.testirovanie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;
import java.util.ResourceBundle;

public class MainMenu extends Configs implements Initializable{

    @FXML
    private Button add_button;

    @FXML
    private Button back_button;

    @FXML
    private Button delete_button;

    @FXML
    private TextField text_callories_product;
    @FXML
    private TableView<Products> list_products;
    @FXML
    private TableColumn<Products, String> callories_id;

    @FXML
    private TableColumn<Products, String> id_table;

    @FXML
    private TableColumn<Products, String> id_grames;

    @FXML
    private TableColumn<Products, String> product_id;

    @FXML
    private TextField text_prosuctName;

    @FXML
    private TextField text_weight_product;

    @FXML
    private Label id_select;

    ObservableList<Products> listM = FXCollections.observableArrayList();

    JOptionPane JOptionPane;

    //добавление продукта из текстфилдов в базу данных, путем соединения через другой объект класса
    @FXML
    void add_product_button(ActionEvent event){
        boolean result = text_prosuctName.getText().matches("[0-9]*") || text_callories_product.getText().matches("[A-zА-я]*") || text_weight_product.getText().matches("[A-zА-я]*");
        if(text_prosuctName.getText() == "" && text_weight_product.getText() == "" &&  text_callories_product.getText() == "" && text_prosuctName.getText().length() < 3){
            JOptionPane.showMessageDialog(null,"данные  не введены");
        }
        else if(result){
            JOptionPane.showMessageDialog(null,"данные введены неправильно");
        }
        else{
            list_products.getItems().clear();
            DatabaseHandler db = new DatabaseHandler();
            String name = text_prosuctName.getText();
            String callories = text_callories_product.getText();
            String grames = text_weight_product.getText();
            Products products = new Products(name, callories, grames);
            db.add_products_db(products);
            try {
                Connection con = db.getDbConnection();

                ResultSet rs = con.createStatement().executeQuery("select * from products");
                product_id.setCellValueFactory(new PropertyValueFactory<>("name_products"));
                callories_id.setCellValueFactory(new PropertyValueFactory<>("callories"));
                id_grames.setCellValueFactory(new PropertyValueFactory<>("grames"));
                id_table.setCellValueFactory(new PropertyValueFactory<>("id"));

                while (rs.next()) {
                    listM.add(new Products(rs.getString("idproducts"), rs.getString("name_products"), rs.getString("callories_product"), rs.getString("grames_product")));
                }

                list_products.setItems(listM);
                text_weight_product.clear();
                text_callories_product.clear();
                text_prosuctName.clear();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    //удаление продукта из базы данных по полученным в приложении из таблицы данных
    @FXML
    void delete_product_button(ActionEvent event) {
        list_products.getItems().clear();
        DatabaseHandler db = new DatabaseHandler();
        try {
            Connection con =  db.getDbConnection();
            PreparedStatement st = con.prepareStatement("SELECT * FROM products WHERE name_products=?");
            String cal = callories_id.getText();



            int rows = st.executeUpdate("DELETE from products WHERE idproducts="   + id_select.getText() );


                ResultSet rs = con.createStatement().executeQuery("select * from products");
                product_id.setCellValueFactory(new PropertyValueFactory<>("name_products"));
                callories_id.setCellValueFactory(new PropertyValueFactory<>("callories"));
                id_grames.setCellValueFactory(new PropertyValueFactory<>("grames"));

                while (rs.next()){
                    listM.add(new Products(rs.getString("idproducts"),rs.getString("name_products"),rs.getString("callories_product"),rs.getString("grames_product")));
                }

                list_products.setItems(listM);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHandler db = new DatabaseHandler();
        try {
            Connection con =  db.getDbConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from products");
            product_id.setCellValueFactory(new PropertyValueFactory<>("name_products"));
            callories_id.setCellValueFactory(new PropertyValueFactory<>("callories"));
            id_grames.setCellValueFactory(new PropertyValueFactory<>("grames"));
            id_table.setCellValueFactory(new PropertyValueFactory<>("id"));
            while (rs.next()){
                listM.add(new Products(rs.getString("idproducts"),rs.getString("name_products"),rs.getString("callories_product"),rs.getString("grames_product")));
            }

            list_products.setItems(listM);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    //метод получения индекса для выбора продукта
    public void OnSelectedProducts(MouseEvent mouseEvent) {
        try{
            int index = list_products.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            id_select.setText(id_table.getCellData(index).toString());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    //кнопка назад
    public void back_button_active(ActionEvent actionEvent) {
        back_button.getScene().getWindow().hide();

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
