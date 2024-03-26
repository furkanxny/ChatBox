package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class registerUIController {

    @FXML
    private TextField ageTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField passwordTF;

    @FXML
    void registerOnAction(ActionEvent event) throws IOException {
    changeScenes(event, "GPT-UI.fxml");
    }

    @FXML
    void signInOnAction(ActionEvent event) throws IOException {
    changeScenes(event, "LogIn.fxml");
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
