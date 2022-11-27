package com.example.cocusmobiletest.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.yaml.snakeyaml.Yaml;

public class TestConfig {
    private static TestConfig testConfig;

    private TestConfig() {
    }

    public static TestConfig getInstance() {
        if (testConfig == null) {
            Path configPath = Paths.get("src\\test\\resources\\config\\testconfig.yml");
            Yaml yaml = new Yaml();
            InputStream in;
            try {
                in = Files.newInputStream(configPath);
                testConfig = yaml.loadAs(in, TestConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return testConfig;
    }

    String platformName;
    String devicename;
    String apkpath;
    String testdatahome;
    String randomuserapiurl;

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public void setApkpath(String apkpath) {
        this.apkpath = apkpath;
    }

    public void setTestdatahome(String testdatahome) {
        this.testdatahome = testdatahome;
    }

    public void setRandomuserapiurl(String randomuserapiurl) {
        this.randomuserapiurl = randomuserapiurl;
    }

    public String getApkpath() {
        return apkpath;
    }

    public String getTestdatahome() {
        return testdatahome;
    }

    public String getRandomuserapiurl() {
        return randomuserapiurl;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

}
