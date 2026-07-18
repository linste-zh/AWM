module linstezh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    //requires ormlite.core;
    requires ormlite.jdbc;
    requires com.opencsv;
    requires jdk.jfr;

    exports linstezh;  // your main package
    exports linstezh.database.dbo;
    opens linstezh.database;
    opens linstezh.database.dbo;
    opens linstezh.database.dao;
    opens linstezh.database.mapper;
    opens linstezh.logic.Item;
    opens linstezh.logic.Section;
    opens linstezh.logic.Experiment;
    opens linstezh.logic.ActiveExperiment;
    opens linstezh.visualisation.adapters;
    opens linstezh.visualisation.screens;
    opens linstezh.executionManagers;
    opens linstezh.exceptions;
}