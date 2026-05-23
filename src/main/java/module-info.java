module linstezh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    //requires ormlite.core;
    requires ormlite.jdbc;

    exports linstezh;  // your main package
    opens linstezh.logic;
}