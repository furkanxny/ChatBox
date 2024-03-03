package com.example.demo;

import FirebaseControllers.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static Firestore fstore;

    private final FirestoreContext contxtFirebase = new FirestoreContext();
    @Override
    public void start(Stage stage) throws IOException {
       // fstore = contxtFirebase.firebase();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}