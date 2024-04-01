package com.example.demo;
import java.awt.Desktop;
import java.net.URI;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    void chatBoxClicked(MouseEvent event) {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI uri = new URI("https://github.com/furkanxny/ChatBox.git");
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void goToChatOnAction(ActionEvent event) throws IOException {
        String emailV = emailTF.getText();
        String passV = passwordTF.getText();
        if (firebase.loginUser(emailV, passV)) {
            changeScenes(event, "GPT-UI.fxml");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("YOUR CREDENTIALS ARE WRONG");
            alert.setContentText("You have entered wrong email or passport");
            alert.showAndWait();
        }
    }


    @FXML
    void registerButtonOnAction(ActionEvent event) throws IOException {
        changeScenes(event,"registerUI.fxml");
    }


    public void changeScenes(ActionEvent event, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }
}