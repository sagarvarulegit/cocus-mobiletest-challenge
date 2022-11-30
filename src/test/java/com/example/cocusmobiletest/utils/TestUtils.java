package com.example.cocusmobiletest.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.example.cocusmobiletest.config.DriverManager;
import com.example.cocusmobiletest.config.TestConfig;
import com.example.cocusmobiletest.stepdefinitions.Hooks;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import io.appium.java_client.imagecomparison.FeatureDetector;
import io.appium.java_client.imagecomparison.FeaturesMatchingOptions;
import io.appium.java_client.imagecomparison.FeaturesMatchingResult;
import io.appium.java_client.imagecomparison.MatchingFunction;

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

    public static void compareImages() {
        // read images as binary data
        File fi = new File(TestConfig.getInstance().getTestdatahome() + "\\dashboard-expected.png");
        byte[] originalImg;
        try {
            originalImg = Files.readAllBytes(fi.toPath());
            byte[] screenshot = Base64.encodeBase64(DriverManager.appiumDriver.getScreenshotAs(OutputType.BYTES));
        FeaturesMatchingResult result = DriverManager.appiumDriver
                .matchImagesFeatures(screenshot, originalImg, new FeaturesMatchingOptions()
                        .withDetectorName(FeatureDetector.ORB)
                        .withGoodMatchesFactor(40)
                        .withMatchFunc(MatchingFunction.BRUTE_FORCE_HAMMING)
                        .withEnabledVisualization());
        } catch (IOException e) {
            e.printStackTrace();
        }

        

        // File actimg = ((TakesScreenshot)
        // Hooks.appiumDriver).getScreenshotAs(OutputType.FILE);
        // try {
        // FileUtils.copyFile(actimg, new File("C:\\SagarV\\actimg.png"));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // // load images to be compared:
        // BufferedImage expectedImage =
        // ImageComparisonUtil.readImageFromResources(TestConfig.getInstance().getTestdatahome()
        // + "\\dashboard-expected.png");
        // BufferedImage actualImage =
        // ImageComparisonUtil.readImageFromResources("C:\\SagarV\\actimg.png");

        // // where to save the result (leave null if you want to see the result in the
        // UI)
        // File resultDestination = new File("C:\\SagarV\\CHECKTHIS.png");

        // // Create ImageComparison object with result destination and compare the
        // images.
        // ImageComparisonResult imageComparisonResult = new
        // ImageComparison(expectedImage, actualImage, resultDestination)
        // .compareImages();

        // if
        // (ImageComparisonState.MATCH.equals(imageComparisonResult.getImageComparisonState()))
        // {
        // return true;
        // } else {
        // return false;
        // }
    }

    public float compareImageSize(File fileA, File fileB) {

        float percentage = 0;
        try {
            // take buffer data from both image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            int count = 0;
            // compare data-buffer objects //
            if (sizeA == sizeB) {

                for (int i = 0; i < sizeA; i++) {

                    if (dbA.getElem(i) == dbB.getElem(i)) {
                        count = count + 1;
                    }

                }
                percentage = (count * 100) / sizeA;
            } else {
                System.out.println("Both the images are not of same size");
            }

        } catch (Exception e) {
            System.out.println("Failed to compare image files ...");
        }
        return percentage;
    }
}
