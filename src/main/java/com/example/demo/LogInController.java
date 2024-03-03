package com.example.demo;

import Models.Person;
import com.google.cloud.firestore.Firestore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private boolean key;

    private Person person;
    private Firestore firestore;



    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();





    @FXML
    void toAIButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GPT-UI.fxml"));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
    }







}