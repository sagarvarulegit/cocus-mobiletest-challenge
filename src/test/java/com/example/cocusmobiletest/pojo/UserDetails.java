package com.example.cocusmobiletest.pojo;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
public class UserDetails{
    @JsonProperty("results") 
    public ArrayList<Result> getResults() { 
		 return this.results; } 
    public void setResults(ArrayList<Result> results) { 
		 this.results = results; } 
    ArrayList<Result> results;
    @JsonProperty("info") 
    public Info getInfo() { 
		 return this.info; } 
    public void setInfo(Info info) { 
		 this.info = info; } 
    Info info;
}

