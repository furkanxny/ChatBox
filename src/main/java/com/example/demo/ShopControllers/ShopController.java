package com.example.demo.ShopControllers;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ShopController implements Initializable {

    @FXML
    private TextField amountTextField;

    @FXML
    private Text creditsText;

    @FXML
    private Button buyButton;

    @FXML
    private Button goBackButton;

    @FXML
    private RadioButton termsButton;

    @FXML
    private Label totalLabel;

    private int tempCount = 0;

    private double total = 0;
    Firebase firebase = new Firebase();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    firebase.setCount(creditsText);
    tempCount = firebase.getMessageCount();
    }


    public void setNewCredits(){
       firebase.setMessageCount(tempCount + Integer.parseInt(amountTextField.getText()));
       firebase.updateDatabase();
       firebase.setCount(creditsText);
       tempCount = firebase.getMessageCount();
       amountTextField.clear();
    }
    @FXML
    void buyButtonOnAction(ActionEvent event) {
        setNewCredits();
    }

    @FXML
    void goBackButtonOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/GPT-UI.fxml"));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void switchProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/ShopProfile.fxml"));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }


}
