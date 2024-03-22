package com.example.demo.AIControllers;

import com.example.demo.FirebaseControllers.Firebase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

    public void setCreditsText(){
        creditsText.setText();
    }

    public void setTotal(){
        total  = .33 * Double.parseDouble((amountTextField.getText()));
        totalLabel.setText(String.valueOf(total));
        tempCount = tempCount + Integer.parseInt( amountTextField.getText());
    }
    @FXML
    void buyButtonOnAction(ActionEvent event) {

    }


}
