package com.example.demo.AiModel;

import com.example.demo.AssistantAIClient;
import com.example.demo.dto.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static java.lang.Thread.sleep;


public class GPTMethods {

    private int messageProcessCount = 0;


    private void waitUntilRunIsFinished(AssistantAIClient client, ThreadResponseDTO thread, RunResponseDTO run, long DELAY) throws InterruptedException {
        while (!isRunDone(client, thread.id(), run.id())) {
            superviseWorkInProgress(client, thread);
            sleep(DELAY * 1000);
        }
    }

    private void superviseWorkInProgress(AssistantAIClient client, ThreadResponseDTO thread) {
        try {
            System.out.println("Checking"); //messages to supervise assistant's work");
            MessagesListResponseDTO messages = client.getMessages(thread.id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void log(MessagesListResponseDTO messages, TextArea outputTF, TextField inputTF) {
        messageProcessCount = 0;
        for (MessageResponseDTO message : messages.data()) {
            if (messageProcessCount < 1) {
                for (MessageResponseDTO.Content content : message.content()) {
                    String textContent = content.text().value();
                    System.out.println(textContent);

                    String newResponse = organizeStringByWordCount(textContent, 20);
                    outputTF.setText(newResponse);

                    messageProcessCount++;
                    inputTF.clear();
                    break;
                }
            }
        }
    }

    private static boolean isRunDone(AssistantAIClient client, String threadId, String runId) {
        RunResponseDTO status;
        try {
            status = client.getRunStatus(threadId, runId);
            System.out.println("Status of your run is currently " + status);
            return isRunStateFinal(status);
        } catch (Exception e) {
            System.err.println("Failed to get run state, will retry..." + e);
            e.printStackTrace();
            return false;
        }
    }

    private static boolean isRunStateFinal(RunResponseDTO runResponseDTO) {
        List<String> finalStates = List.of("cancelled", "failed", "completed", "expired"); //I consider these states as final
        String runStatus = Optional.of(runResponseDTO).map(RunResponseDTO::status).orElse("unknown").toLowerCase();
        return finalStates.contains(runStatus);
    }

    public void initializeAssistant(TextArea outputTF, TextField inputTF) {
        Properties properties = new Properties();
        long DELAY = 3;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);

            AssistantAIClient client = new AssistantAIClient(properties);
            AssistantResponseDTO assistant = client.createAssistant(properties.getProperty("openai.assistant.instructions4"));
            ThreadResponseDTO thread = client.createThread();
            client.sendMessage(thread.id(), "user", inputTF.getText());
            RunResponseDTO run = client.runMessage(thread.id(), assistant.id());

            waitUntilRunIsFinished(client, thread, run, DELAY);

            MessagesListResponseDTO allResponses = client.getMessages(thread.id());
            log(allResponses, outputTF, inputTF);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String organizeStringByWordCount(String input, int wordCount) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();
        int count = 0;

        for (String word : words) {
            result.append(word).append(" ");
            count++;


            if (count % wordCount == 0) {
                result.append("\n");
            }
        }

        return result.toString().trim();
    }


}