package com.example.demo;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    @FXML
    private TextField emailTF;
    @FXML
    private PasswordField passwordTF;
    Firebase firebase;

    @FXML
    private void initialize() {
        firebase = new Firebase();
    }

    @FXML
    void changeSceneToChat(ActionEvent event) throws IOException {
        String emailV = emailTF.getText();
        String passV = passwordTF.getText();
        firebase.readFirebase();
        if (firebase.loginUser(emailV, passV)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GPT-UI.fxml"));
            Parent secondViewRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(secondViewRoot);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.centerOnScreen();

        } else {
            System.out.println(firebase.loginUser(emailV, passV));
        }
    }
}