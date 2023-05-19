package com.example.cocusmobiletest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    tags= "@poc",
    features="src/test/resources/features",
plugin = {"json:target/cucumber-report/cucumber.json","pretty","html:target/cucumber-report/cucumber.html"}
)
public class RunCukesTest {
   
}
    
