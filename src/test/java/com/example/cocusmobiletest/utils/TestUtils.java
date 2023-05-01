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
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;

import static org.junit.Assert.assertThat;

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

    /**
     * Experimental method to compare images using Appium's image comparison
     * feature.
     */
    public static void compareImagesUsingAppium() {
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

            byte[] screenshot1 = Base64.encodeBase64(DriverManager.appiumDriver.getScreenshotAs(OutputType.BYTES));
            byte[] screenshot2 = Base64.encodeBase64(DriverManager.appiumDriver.getScreenshotAs(OutputType.BYTES));
            SimilarityMatchingResult result1 = DriverManager.appiumDriver
                    .getImagesSimilarity(screenshot1, screenshot2, new SimilarityMatchingOptions()
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

    /**
     * Experimental method to compare images using Appium's image comparison
     * feature.
     */

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

    public static void compareImageChatGPT() throws IOException {
        BufferedImage image1 = ImageIO
                .read(new File(TestConfig.getInstance().getTestdatahome() + "\\dashboard-expected.png"));
        File screenshot = DriverManager.appiumDriver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("C:\\SagarV\\actimg1.png"));

        BufferedImage image2 = ImageIO.read(screenshot);

        int width1 = image1.getWidth();
        int height1 = image1.getHeight();
        int width2 = image2.getWidth();
        int height2 = image2.getHeight();
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgb1 = image1.getRGB(x, y);
                int red1 = (rgb1 >> 16) & 0xff;
                int green1 = (rgb1 >> 8) & 0xff;
                int blue1 = rgb1 & 0xff;
                int gray1 = (int) (0.2989 * red1 + 0.5870 * green1 + 0.1140 * blue1);
                image1.setRGB(x, y, (gray1 << 16) | (gray1 << 8) | gray1);
                int rgb2 = image2.getRGB(x, y);
                int red2 = (rgb2 >> 16) & 0xff;
                int green2 = (rgb2 >> 8) & 0xff;
                int blue2 = rgb2 & 0xff;
                int gray2 = (int) (0.2989 * red2 + 0.5870 * green2 + 0.1140 * blue2);
                image2.setRGB(x, y, (gray2 << 16) | (gray2 << 8) | gray2);

                // save buffered image to file
                File outputfile = new File("C:\\SagarV\\CHECKTHIS1.png");
                ImageIO.write(image1, "png", outputfile);

                File outputfile2 = new File("C:\\SagarV\\CHECKTHIS2.png");
                ImageIO.write(image2, "png", outputfile2);
            }
        }

        long totalDiff = 0;
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgb1 = image1.getRGB(x, y);
                int gray1 = rgb1 & 0xff;
                int rgb2 = image2.getRGB(x, y);
                int gray2 = rgb2 & 0xff;
                totalDiff += Math.abs(gray1 - gray2);
            }
        }
        double avgDiff = (double) totalDiff / (width1 * height1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Average difference: " + avgDiff);

    }

}
