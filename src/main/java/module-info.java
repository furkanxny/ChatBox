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



    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports AIControllers;
    opens AIControllers to javafx.fxml;
}