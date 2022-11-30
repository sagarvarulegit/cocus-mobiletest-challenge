package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Picture{
    @JsonProperty("large") 
    public String getLarge() { 
		 return this.large; } 
    public void setLarge(String large) { 
		 this.large = large; } 
    String large;
    @JsonProperty("medium") 
    public String getMedium() { 
		 return this.medium; } 
    public void setMedium(String medium) { 
		 this.medium = medium; } 
    String medium;
    @JsonProperty("thumbnail") 
    public String getThumbnail() { 
		 return this.thumbnail; } 
    public void setThumbnail(String thumbnail) { 
		 this.thumbnail = thumbnail; } 
    String thumbnail;
}
