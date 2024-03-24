package com.example.demo.ShopControllers;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ShopController implements Initializable {

    @FXML
    private TextField amountsWanted;

    @FXML
    private Text avaliableCredits;

    @FXML
    private RadioButton termsButton;

    private int tempCount = 0;

    Firebase firebase = new Firebase();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    firebase.setCredit(avaliableCredits);
    tempCount = firebase.getCredit();
    }


    public void setNewCredits(){
        if(!amountsWanted.getText().isEmpty() && Integer.parseInt(amountsWanted.getText()) > 0 && Integer.parseInt(amountsWanted.getText()) < 20){
       firebase.setNewCredit(tempCount + Integer.parseInt(amountsWanted.getText()));
       firebase.updateDatabase();
       firebase.setCredit(avaliableCredits);
       tempCount = firebase.getCredit();
       amountsWanted.clear();
       termsButton.setSelected(false);
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("WRONG INPUT");
            alert.setContentText("Please enter an amount between 1 - 20");
            alert.showAndWait();
        }

    }

    @FXML
    void buyButtonOnAction(ActionEvent event) {
        if(termsButton.isSelected()){
            setNewCredits();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("YOU HAVE NOT ACCEPTED THE TERM AND AGREEMENTS!");
            alert.setContentText("You have to accept the terms and the agreements to buy more credits");
            alert.showAndWait();
        }
    }

    @FXML
    void goToChatOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/GPT-UI.fxml"));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }

    @FXML
    void goToProfileOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/ShopProfile.fxml"));
        Parent secondViewRoot = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(secondViewRoot);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
    }


}
