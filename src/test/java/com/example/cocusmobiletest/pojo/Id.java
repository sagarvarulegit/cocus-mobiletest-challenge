package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Id{
    @JsonProperty("name") 
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
    @JsonProperty("value") 
    public Object getValue() { 
		 return this.value; } 
    public void setValue(Object value) { 
		 this.value = value; } 
    Object value;
}
