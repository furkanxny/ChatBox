module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.auth.oauth2;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.auth;
    requires com.google.api.apicommon;
    requires java.logging;
    requires google.cloud.core;
    requires com.google.gson;
    requires okhttp3;
    requires com.fasterxml.jackson.annotation;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens com.example.demo.dto to com.fasterxml.jackson.databind;

    opens com.example.demo.OpenAI to com.google.gson;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.AIControllers;
    opens com.example.demo.AIControllers to javafx.fxml;
    exports com.example.demo.ShopControllers;
    opens com.example.demo.ShopControllers to javafx.fxml;
}