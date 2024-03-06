//package com.example.demo.OpenAI;
//
//import com.google.gson.Gson;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//
//import java.io.IOException;
//import java.util.List;
//
//public class OpenAIRequestHandler {
//    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
//   // private static final String API_KEY = "Secret Key";
//    private static final OkHttpClient client = new OkHttpClient();
//    private static final Gson gson = new Gson();
//    //public static Gson getGson() {
//        return gson;
//    }
//
//
//
//
//
////    public static String makeRequest(String prompt) throws IOException {
////        String model = "gpt-3.5-turbo"; // Make sure this is a valid model
////        List<RequestBodyData.Message> messages = List.of(new RequestBodyData.Message("user", prompt)); // Starting with a user message
////
////        var body = gson.toJson(new RequestBodyData(messages, model));
////        var requestBody = RequestBody.create(body, MediaType.get("application/json; charset=utf-8"));
////
////        Request request = new Request.Builder()
////                .url(API_URL)
////                .addHeader("Authorization", "Bearer " + API_KEY)
////                .post(requestBody)
////                .build();
////
////        try (Response response = client.newCall(request).execute()) {
////            return response.body().string();
////        }
////    }
////}
//
////
////    public static String makeRequest(String prompt) throws IOException {
////        String model = "gpt-3.5-turbo";
////        var body = gson.toJson(new RequestBodyData(prompt, model));
////        var requestBody = RequestBody.create(body, MediaType.get("application/json; charset=utf-8"));
////
////        Request request = new Request.Builder()
////                .url(API_URL)
////                .addHeader("Authorization", "Bearer " + API_KEY)
////                .post(requestBody)
////                .build();
////
////        try (Response response = client.newCall(request).execute()) {
////            return response.body().string();
////        }
////    }
//
