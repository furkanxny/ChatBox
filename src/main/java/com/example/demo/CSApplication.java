package com.example.demo;

import com.example.demo.FirebaseControllers.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CSApplication extends javafx.application.Application {
    private static Firestore fstore;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        FXMLLoader fxmlLoader = new FXMLLoader(CSApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("CHATBOX");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
    public static synchronized Firestore getFirestore() {
        return fstore;
    }

    public static void main(String[] args) {
        launch();
    }
}