module org.example.mmschulzfinalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens org.example.mmschulzfinalproject to javafx.fxml;
    exports org.example.mmschulzfinalproject;
}