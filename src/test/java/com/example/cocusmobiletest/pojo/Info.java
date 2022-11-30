package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Info{
    @JsonProperty("seed") 
    public String getSeed() { 
		 return this.seed; } 
    public void setSeed(String seed) { 
		 this.seed = seed; } 
    String seed;
    @JsonProperty("results") 
    public int getResults() { 
		 return this.results; } 
    public void setResults(int results) { 
		 this.results = results; } 
    int results;
    @JsonProperty("page") 
    public int getPage() { 
		 return this.page; } 
    public void setPage(int page) { 
		 this.page = page; } 
    int page;
    @JsonProperty("version") 
    public String getVersion() { 
		 return this.version; } 
    public void setVersion(String version) { 
		 this.version = version; } 
    String version;
}
