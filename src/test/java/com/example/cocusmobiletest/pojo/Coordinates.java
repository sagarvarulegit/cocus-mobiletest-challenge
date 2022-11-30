package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Coordinates{
    @JsonProperty("latitude") 
    public String getLatitude() { 
		 return this.latitude; } 
    public void setLatitude(String latitude) { 
		 this.latitude = latitude; } 
    String latitude;
    @JsonProperty("longitude") 
    public String getLongitude() { 
		 return this.longitude; } 
    public void setLongitude(String longitude) { 
		 this.longitude = longitude; } 
    String longitude;
}

