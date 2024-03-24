package com.example.demo.FirebaseControllers;

import com.example.demo.Models.Person;
import com.example.demo.Application;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Firebase {
    private ArrayList<String> registeredEmailArryList = new ArrayList<>();
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private boolean key;
    private Person person;
    private static String ID;
    private static int messageCount;
    private static int gpt1;
    private static int gpt2;
    private static int gpt3;
    private static int gpt4;
    private Firestore firestore = Application.fstore;

    public ObservableList<Person> getListOfUsers() {
        return listOfUsers;
    }

    public Firebase() {
    }

    public void setGpt1(int gpt1) {
        this.gpt1 = gpt1;
    }

    public void setGpt2(int gpt2) {
        this.gpt2 = gpt2;
    }

    public void setGpt3(int gpt3) {
        this.gpt3 = gpt3;
    }

    public void setGpt4(int gpt4) {
        this.gpt4 = gpt4;
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

    public void setChatGPTModels(RadioButton radioButton1, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4) {
        if (Firebase.gpt1 == 0) {
            radioButton1.setDisable(true);
        }
        if (Firebase.gpt2 == 0) {
            radioButton2.setDisable(true);
        }
        if (Firebase.gpt3 == 0) {
            radioButton3.setDisable(true);
        }
        if (Firebase.gpt4 == 0) {
            radioButton4.setDisable(true);
        }


    }

    public int getCredit() {
        return messageCount;
    };


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
                    person = new Person(
                            String.valueOf(document.getData().get("name")),
                            String.valueOf(document.getData().get("lastName")),
                            String.valueOf(document.getData().get("email")),
                            String.valueOf(document.getData().get("Password")),
                            Integer.parseInt(document.getData().get("age").toString()),
                            Integer.parseInt(document.getData().get("messageCount").toString()),
                            Integer.parseInt(document.getData().get("gpt1").toString()),
                            Integer.parseInt(document.getData().get("gpt2").toString()),
                            Integer.parseInt(document.getData().get("gpt2").toString()),
                            Integer.parseInt(document.getData().get("gpt4").toString())
                    );
                    setNewCredit(Integer.parseInt(document.getData().get("messageCount").toString()));
                    setGpt1(Integer.parseInt(document.getData().get("gpt1").toString()));
                    setGpt2(Integer.parseInt(document.getData().get("gpt2").toString()));
                    setGpt3(Integer.parseInt(document.getData().get("gpt3").toString()));
                    setGpt4(Integer.parseInt(document.getData().get("gpt4").toString()));
                    System.out.println(Integer.parseInt(document.getData().get("gpt1").toString()));
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
        updates.put("gpt1", gpt1);
        updates.put("gpt2", gpt2);
        updates.put("gpt3", gpt3);
        updates.put("gpt4", gpt4);

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
