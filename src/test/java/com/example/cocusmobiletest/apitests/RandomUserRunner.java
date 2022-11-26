package com.example.cocusmobiletest.apitests;

import com.intuit.karate.junit5.Karate;

public class RandomUserRunner {
    @Karate.Test
    public Karate testRandomUserRunner(){
        return Karate.run("RandomUser").relativeTo(getClass());
    }
}
