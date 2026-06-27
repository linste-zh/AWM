module linstezh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    //requires ormlite.core;
    requires ormlite.jdbc;

    exports linstezh;  // your main package
    exports linstezh.database.dbObjects;
    opens linstezh.database.dbObjects;
    opens linstezh.logic;
    opens linstezh.database;
    opens linstezh.visualisation;
}