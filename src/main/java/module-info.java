module linstezh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    //requires ormlite.core;
    requires ormlite.jdbc;

    exports linstezh;  // your main package
    exports linstezh.database.dbo;
    opens linstezh.database.dbo;
    opens linstezh.logic;
    opens linstezh.database;
    opens linstezh.visualisation;
    opens linstezh.logic.Experiment;
    opens linstezh.logic.Item;
    opens linstezh.logic.ActiveExperiment;
    opens linstezh.logic.Section;
}