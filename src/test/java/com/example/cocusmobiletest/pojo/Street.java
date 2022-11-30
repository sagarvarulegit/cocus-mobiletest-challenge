package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Street{
    @JsonProperty("number") 
    public int getNumber() { 
		 return this.number; } 
    public void setNumber(int number) { 
		 this.number = number; } 
    int number;
    @JsonProperty("name") 
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
}
