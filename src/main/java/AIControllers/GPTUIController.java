package AIControllers;

import OpenAI.OpenAIRequestHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GPTUIController {
    @FXML
    private TextField inputTF;

    @FXML
    private TextArea outputTF;

    @FXML
    void enterButtonOnAction(ActionEvent event) {
        GetResponse();
    }

    public static String organizeStringByWordCount(String input, int wordCount) {
        String[] words = input.split(" "); // Split the string into words
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (String word : words) {
            result.append(word).append(" ");
            count++;

            // After every 'wordCount' words, append a newline
            if (count % wordCount == 0) {
                result.append("\n");
            }
        }

        return result.toString().trim(); // Return the result, trimming any trailing whitespace
    }


    public void GetResponse() {
        try {
            String promptText = inputTF.getText();
            String responseJson = OpenAIRequestHandler.makeRequest(promptText);
            JsonObject jsonObject = JsonParser.parseString(responseJson).getAsJsonObject();
            String content = jsonObject.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();
            String resultString = organizeStringByWordCount(content, 10);
            outputTF.setText(resultString);
            inputTF.clear();
        } catch (IOException e) {
            outputTF.setText("Error: " + e.getMessage());
        }
    }

}
