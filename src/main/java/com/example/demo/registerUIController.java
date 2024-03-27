package com.example.demo;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class registerUIController implements Initializable {

    @FXML
    private TextField ageTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField nameTF;

    @FXML
    private PasswordField passwordTF;

    Firebase firebase;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firebase = new Firebase();
    }

    @FXML
    void registerOnAction(ActionEvent event) throws IOException {
        firebase.readFirebase();
        if(firebase.isEmailExist(emailTF)){
            firebase.addData(emailTF, nameTF, ageTF, passwordTF);
            changeScenes(event, "GPT-UI.fxml");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("EMAIL ALREADY EXIST");
            alert.setContentText("This email already exists, please login or use another email!");
            alert.showAndWait();
        }

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
