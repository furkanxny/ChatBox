package com.example.demo.AIControllers;

import com.example.demo.AiModel.GPTMethods;


import com.example.demo.FirebaseControllers.Firebase;
import com.example.demo.Models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Properties;
import java.util.ResourceBundle;

public class GPTUIController implements Initializable {
    @FXML
    private Text count;
    @FXML
    private Button enterButton;
    @FXML
    private TextField inputTF;
    @FXML
    private TextArea outputTF;
    @FXML
    private RadioButton statelessRadioButton;
    @FXML
    private RadioButton friendlyRadioButton;
    @FXML
    private RadioButton stupidRadioButton;
    @FXML
    private RadioButton angeryRadioButton;
    GPTMethods gptMethods = new GPTMethods();
    private String initialPrompt;

    Firebase firebase = new Firebase();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firebase.setMessageLimit(count);
    }


    private void checkMessageCount(){

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

        if (friendlyRadioButton.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.friendly");
        } else if (angeryRadioButton.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.annoyed");
        } else if (stupidRadioButton.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.stupid");
        } else {
            initialPrompt = properties.getProperty("openai.assistants.prompt.stateless");
        }
        gptMethods.initializeAssistant(outputTF, inputTF, initialPrompt);
        System.out.println(initialPrompt);
    }


    @FXML
    void enterButtonOnAction(ActionEvent event) throws IOException {
        //checkMessageCount();
        if(firebase.getMessageCount() <= -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No");
            alert.setContentText("Yes");
            alert.setHeaderText("Ok");
            alert.showAndWait();
            return;
        }
        else
        firebase.setMessageLimit(count);
        enterButton.setDisable(true);
        setInitialPrompt();
        //count.setText(String.valueOf(gptMethods.counter()));
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event1 -> enterButton.setDisable(false));
        pause.play();

    }



}
