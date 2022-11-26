package com.example.cocusmobiletest.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TestUtils {
    public static String writeToJSONFile(String text, String filename) {

        try {
            FileWriter writer = new FileWriter(
                    TestSuite.TEST_DATA_HOME + filename,
                    false);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "PASS";
    }

    public static void getRandomUserDataFromAPI() {
        try {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create(TestSuite.RANDOM_USER_APIURL))
                .header("accept", "application/json")
                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                writeToJSONFile(response.body(), "randomuser.json");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
