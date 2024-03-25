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
    private RadioButton statelessRadioButton;
    @FXML
    private RadioButton angryRadioButton;
    @FXML
    private RadioButton stupidRadioButton;
    @FXML
    private RadioButton friendlyRadioButton;

    @FXML
    private Button enterButton;
    GPTMethods gptMethods = new GPTMethods();
    private String initialPrompt;
    Firebase firebase = new Firebase();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firebase.setCredit(count);
        firebase.setChatGPTModels(statelessRadioButton, angryRadioButton, stupidRadioButton, friendlyRadioButton);
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
        } else if (angryRadioButton.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.annoyed");
        } else if (stupidRadioButton.isSelected()) {
            initialPrompt = properties.getProperty("openai.assistants.prompt.stupid");
        } else if (statelessRadioButton.isSelected()){
            initialPrompt = properties.getProperty("openai.assistants.prompt.stateless");
            gptMethods.initializeAssistant2(outputTF, inputTF, initialPrompt);
            System.out.println();

        }
//        gptMethods.initializeAssistant(outputTF, inputTF, initialPrompt);
//        System.out.println(initialPrompt);
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
