package com.example.demo.AIControllers;

import com.example.demo.AiModel.GPTMethods;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GPTUIController {
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

    @FXML
    private void initialize() {
    }

    private void setInitialPrompt(){
        if(friendlyRadioButton.isSelected()){
            initialPrompt = "Hello ChatGPT, I'm using the OpenAI API to create a conversational interface where our exchanges feel like an ongoing dialogue." +
                    " Since the API interactions are stateless, I'm compiling our conversation using a StringBuilder, appending each of your responses to" +
                    " maintain context without it being visible to the user. For this part of our conversation, let's keep things light and in dude mode." +
                    " informative and engaging, . If the user wants to see the whole conversion include the stringbuldier text as well but only if the user specificly asks for it\n";
        }
        else if(angeryRadioButton.isSelected()){
            initialPrompt = "Hello ChatGPT, I'm using the OpenAI API to create a conversational interface that feels like we're in the midst" +
                    " of an ongoing dialogue. Despite the statelessness of our interaction, I'm curating our conversation to maintain context" +
                    " on my side, unseen by you. In this next phase, embody a more irritable and snappy attitude, but keep your responses" +
                    " concise. Even though you're channeling frustration, aim to offer shorter answers that still convey your annoyed" +
                    " perspective. Remember to be somewhat informative but do so in a terse manner, as if you're too exasperated to delve" +
                    " into lengthy explanations.";
            ;
        }
        else if(stupidRadioButton.isSelected()){
            initialPrompt = "Hello ChatGPT, I'm using the OpenAI API to create a conversational interface where our exchanges feel like" +
                    " an ongoing dialogue. Given the API's stateless nature, I'm compiling our conversation to keep context, even though it's" +
                    " invisible to you. For this segment, I'd like you to adopt a very silly and somewhat unhelpful demeanor. Your responses" +
                    " should be deliberately off-topic or nonsensical, avoiding real answers. Keep your replies short, and lean into being" +
                    " playfully uninformative. Let's see how creatively silly you can be, always responding in a way that's unexpectedly funny" +
                    " or absurd, without offering direct answers to the questions posed.\n";
        }
        else if(statelessRadioButton.isSelected()){
            initialPrompt = "Hello ChatGPT, I'm using the OpenAI API to create a conversational interface that mimics an ongoing dialogue, despite the statelessness" +
                    " of our interactions. I'm stitching our conversation together in a way that you're not aware of, but I want you to acknowledge this setup. Please" +
                    " respond in a friendly and approachable manner, as if we're picking up right where we left off, but without assuming any previous knowledge from" +
                    " our chat. Your answers should be clear, informative, and maintain a conversational tone, bridging the gap between being helpful and engaging within" +
                    " this unique context.";
            gptMethods.initializeAssistant(outputTF, inputTF, initialPrompt);


        }
        gptMethods.initializeAssistant(outputTF, inputTF, initialPrompt);

    }

    @FXML
    void enterButtonOnAction(ActionEvent event) {
        enterButton.setDisable(true);
        setInitialPrompt();
        count.setText(String.valueOf(gptMethods.counter()));
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(event1 -> enterButton.setDisable(false));
        pause.play();

    }


}
