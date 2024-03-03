module com.trimigos.trimigosbooks {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.graphics;
    requires java.logging;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;


    opens com.trimigos to javafx.fxml;

    exports com.trimigos;
    exports com.trimigos.controllers;
    exports com.trimigos.models;
    exports com.trimigos.views;
}