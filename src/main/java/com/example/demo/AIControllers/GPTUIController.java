package com.example.demo.AIControllers;

import com.example.demo.AiModel.GPTMethods;
import com.example.demo.FirebaseControllers.Firebase;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class GPTUIController implements Initializable {
    @FXML
    private Text count;
    @FXML
    private TextField inputTF;
    @FXML
    private TextArea outputTF;
    @FXML
    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12;
    @FXML
    private Button enterButton;
    GPTMethods gptMethods = new GPTMethods();
    private String initialPrompt;
    Firebase firebase = new Firebase();
    RadioButton[] radioButtonsArry;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioButtonsArry = new RadioButton[]{r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12};
        firebase.setCredit(count);
        firebase.setChatGPTModels(radioButtonsArry);
    }



    private void setInitialPrompt() throws IOException {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        }
        if (r4.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.friendly");
        } else if (r2.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.annoyed");
        } else if (r3.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.stupid");
        } else if (r1.isSelected()){
            initialPrompt = properties.getProperty("openai.assistants.prompt.stateless");
            gptMethods.initializeAssistant2(outputTF, inputTF, initialPrompt);
        }
    }

    @FXML
    void goToCreditsOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/Shop.fxml"));
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


    @FXML
    void enterButtonOnAction(ActionEvent event) throws IOException {
        if (firebase.getCredit() <= -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OVER LIMIT");
            alert.setHeaderText("YOU HAVE USED ALL YOUR CREDIT!");
            alert.setContentText("You have used all your credits but you can buy more.");
            alert.showAndWait();
            return;
        }
        firebase.setMessageLimit(count);
        enterButton.setDisable(true);
        setInitialPrompt();
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event1 -> enterButton.setDisable(false));
        pause.play();
        firebase.updateDatabase();
    }


}
