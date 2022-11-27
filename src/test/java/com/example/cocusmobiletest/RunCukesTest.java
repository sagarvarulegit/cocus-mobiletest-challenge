package com.example.cocusmobiletest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(tags="@wip",plugin = {"pretty", "html:target/cucumber-html-report.html"})
public class RunCukesTest {
}
