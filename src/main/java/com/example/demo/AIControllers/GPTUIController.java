package com.example.demo.AIControllers;

import com.example.demo.AiModel.GPTMethods;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GPTUIController {
    @FXML
    private TextField inputTF;
    @FXML
    private TextArea outputTF;
    GPTMethods gptMethods = new GPTMethods();

    @FXML
    private void initialize() {
    }

    @FXML
    void enterButtonOnAction(ActionEvent event) {
        gptMethods.initializeAssistant(outputTF, inputTF);
    }


}
