module com.example.testirovanie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.testirovanie to javafx.fxml;
    exports com.example.testirovanie;
}