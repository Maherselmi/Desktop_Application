module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires ojdbc6;

    opens org.example to javafx.fxml;
    exports org.example;
}