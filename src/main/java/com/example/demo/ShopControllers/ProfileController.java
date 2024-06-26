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
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    Firebase firebase = new Firebase();
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
    Button[] buttonArry;
    Button[] buttons = new Button[]{b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonArry = new Button[]{b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
        firebase.setShopGPTModels(buttonArry);
        callButtons();
    }


    public void callButtons() {

        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(1);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b2.setText("Bought");
        });

        b3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(2);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b3.setText("Bought");
        });

        b4.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(3);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b4.setText("Bought");
        });

        b5.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(4);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b5.setText("Bought");
        });

        b6.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(5);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b6.setText("Bought");
        });

        b7.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(6);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b7.setText("Bought");
        });


        b8.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(7);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b8.setText("Bought");
        });

        b9.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(8);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b9.setText("Bought");
        });

        b10.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(9);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b10.setText("Bought");
        });

        b11.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(10);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b11.setText("Bought");
        });

        b12.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            firebase.buyGPT(11);
            firebase.updateDatabase();
            firebase.setShopGPTModels(buttonArry);
            b12.setText("Bought");
        });
    }

    @FXML
    void goToCreditsOnAction(ActionEvent event) throws IOException {
        changeScenes(event, "/com/example/demo/Shop.fxml");
    }

    @FXML
    void goToChatOnAction(ActionEvent event) throws IOException {
        changeScenes(event, "/com/example/demo/GPT-UI.fxml");
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
