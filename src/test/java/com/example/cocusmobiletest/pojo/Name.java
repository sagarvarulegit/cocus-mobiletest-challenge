package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Name{
    @JsonProperty("title") 
    public String getTitle() { 
		 return this.title; } 
    public void setTitle(String title) { 
		 this.title = title; } 
    String title;
    @JsonProperty("first") 
    public String getFirst() { 
		 return this.first; } 
    public void setFirst(String first) { 
		 this.first = first; } 
    String first;
    @JsonProperty("last") 
    public String getLast() { 
		 return this.last; } 
    public void setLast(String last) { 
		 this.last = last; } 
    String last;
}
