package com.example.cocusmobiletest.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.example.cocusmobiletest.config.TestConfig;

public class TestUtils {
    public static String writeToJSONFile(String text, String filename) {

        try {
            FileWriter writer = new FileWriter(
                    TestConfig.getInstance().getTestdatahome() + filename,
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
                    URI.create(TestConfig.getInstance().getRandomuserapiurl()))
                .header("accept", "application/json")
                .build();
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                writeToJSONFile(response.body(), "randomuser.json");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static String readFile(String fileName) {
        String content = "";
        try {
            content = Files.readString(Paths.get(TestConfig.getInstance().getTestdatahome() + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
