package com.example.demo.FirebaseControllers;

import com.example.demo.Models.Person;
import com.example.demo.Application;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Firestore firestore = Application.fstore;
    public ObservableList<Person> getListOfUsers() {
        return listOfUsers;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setMessageCount(int messageCount){
        Firebase.messageCount = messageCount;}

    public Firebase() {
    }

    public void setMessageLimit(Text count){
        count.setText(String.valueOf(messageCount--));
    }

    public void setCount(Text count){
        count.setText(String.valueOf(messageCount));
    }

    public int getMessageCount(){
        return messageCount;
    }

    ;

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
                            Integer.parseInt(document.getData().get("messageCount").toString())
                    );
                    setMessageCount(Integer.parseInt(document.getData().get("messageCount").toString()));
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

    public boolean updateDatabase(){
        DocumentReference docRef = Application.fstore.collection("Persons")
                .document(ID);
        Map<String, Object> updates = new HashMap<>();
        updates.put("messageCount", messageCount);

        ApiFuture<WriteResult> writeResult = docRef.update(updates);
        try {
            writeResult.get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void addData(String name, String lastName, String email, String password, int age) {
        readFirebase();
        DocumentReference docRef = Application.fstore.collection("Persons").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("lastName", lastName);
        data.put("email", email);
        data.put("Password", password);
        data.put("age", age);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("User registration is successful");
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
