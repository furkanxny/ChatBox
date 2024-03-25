package com.example.demo.FirebaseControllers;

import com.example.demo.Models.Person;
import com.example.demo.Application;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Firebase {
    private ArrayList<String> registeredEmailArryList = new ArrayList<>();
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private boolean key;
    private Person person;
    private static String ID;
    private static int messageCount;
    private static int gpt1, gpt2, gpt3, gpt4, gpt5, gpt6, gpt7, gpt8, gpt9, gpt10, gpt11, gpt12;
    static int[] gptArry = new int[]{gpt1, gpt2, gpt3, gpt4, gpt5, gpt6, gpt7, gpt8, gpt9, gpt10, gpt11, gpt12};
    private Firestore firestore = Application.fstore;


    public Firebase() {
    }

    public void setGpt(int gpt, int i) {
        this.gptArry[i - 1] = gpt;
    }

    public void buyGPT(int arrayCount){
        this.gptArry[arrayCount] = 1;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNewCredit(int messageCount) {
        Firebase.messageCount = messageCount;
    }

    public void setMessageLimit(Text count) {
        count.setText(String.valueOf(messageCount--));
    }

    public void setCredit(Text count) {
        count.setText(String.valueOf(messageCount));
    }

    public void setChatGPTModels(RadioButton[] radioButtonsArry) {
        for (int i = 0; i < gptArry.length; i++) {
            if (gptArry[i] == 0) {
                radioButtonsArry[i].setDisable(true);
            }
        }
    }

    public void setShopGPTModels(Button[] buttonArry){
        for(int i = 1; i < gptArry.length; i++){
            if(gptArry[i] == 0){
                buttonArry[i].setDisable(false);
            }
            else{
                buttonArry[i].setDisable(true);
            }
        }
    }

    public int getCredit() {
        return messageCount;
    }


    public boolean readFirebase() {
        key = false;
        ApiFuture<QuerySnapshot> future = Application.fstore.collection("Persons").get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                System.out.println("Outing data from firabase database....");
                listOfUsers.clear();
                for (QueryDocumentSnapshot document : documents) {
                    registeredEmailArryList.add((String) document.getData().get("email"));
                    System.out.println(document.getId() + " => " + document.getData().get("name"));
                    setNewCredit(Integer.parseInt(document.getData().get("messageCount").toString()));

                    for (int i = 1; i < gptArry.length + 1; i++) {
                        String key = "gpt" + i;
                        setGpt(Integer.parseInt(document.getData().get(key).toString()), i);
                    }

                    listOfUsers.add(person);
                }
            } else {
                System.out.println("No data");
            }
            key = true;
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return key;

    }

    public boolean updateDatabase() {
        DocumentReference docRef = Application.fstore.collection("Persons")
                .document(ID);
        Map<String, Object> updates = new HashMap<>();
        updates.put("messageCount", messageCount);

        for(int i = 1; i < gptArry.length; i++){
            String key = "gpt" + (i+1);
            System.out.println(key);
            updates.put(key, gptArry[i]);
        }

        ApiFuture<WriteResult> writeResult = docRef.update(updates);
        try {
            writeResult.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean loginUser(String email, String password) {
        ApiFuture<QuerySnapshot> future = firestore.collection("Persons").whereEqualTo("email", email).get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    String storedPassword = document.getString("password"); // Make sure the field name matches exactly what's in Firestore
                    if (storedPassword != null && storedPassword.equals(password)) {
                        setID(document.getId());
                        return true;
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }


}
