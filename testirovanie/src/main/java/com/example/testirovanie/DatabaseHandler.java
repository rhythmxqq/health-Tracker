package com.example.testirovanie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

//метод подключения к бд
public class DatabaseHandler extends Configs {
    Connection dbConnection;
   public Connection getDbConnection() throws ClassNotFoundException, SQLException{
       String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
       dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
       return dbConnection;
   }

//добавления продукта в бд
    public void add_products_db(Products products){
       String insert = "INSERT INTO " + Const.PRODUCTS_TABLE+"(" + Const.PRODUCTS_NAME + ","
                + Const.PRODUCTS_CALLORIES + ", " + Const.PRODUCTS_GRAMES + ")" + "VALUES (?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, products.getName_products());
            prSt.setString(2, products.getCallories());
            prSt.setString(3, products.getGrames());

            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //регистрация нового пользователя
   public void signUpUser(User user){
       String insert = "INSERT INTO " + Const.USER_TABLE+"(" + Const.USER_FIRSTNAME + ","
               + Const.USER_LASTNAME + ", " + Const.USER_USERNAME + "," + Const.USER_PASSWORD
               + "," + Const.USER_LOCATION + "," + Const.USER_GENDER + ")" + "VALUES (?,?,?,?,?,?)";
       try {
           PreparedStatement prSt = getDbConnection().prepareStatement(insert);
           prSt.setString(1, user.getFirstName());
           prSt.setString(2, user.getLastName());
           prSt.setString(3, user.getUserName());
           prSt.setString(4, user.getPassword());
           prSt.setString(5, user.getLocation());
           prSt.setString(6, user.getGender());
           prSt.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
   }

//проверка логина и пароля пользователя
   public ResultSet getUser(User user){
           ResultSet resSet = null;

       try {

           String select = "select * from users where username =? AND password =?";
           PreparedStatement prSt = getDbConnection().prepareStatement(select);
           prSt.setString(1, user.getUserName());
           prSt.setString(2, user.getPassword());

           resSet =  prSt.executeQuery();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
       return resSet;
   }

}
