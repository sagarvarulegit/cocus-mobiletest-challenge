package com.example.cocusmobiletest.pojo;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Registered{
    @JsonProperty("date") 
    public Date getDate() { 
		 return this.date; } 
    public void setDate(Date date) { 
		 this.date = date; } 
    Date date;
    @JsonProperty("age") 
    public int getAge() { 
		 return this.age; } 
    public void setAge(int age) { 
		 this.age = age; } 
    int age;
}
