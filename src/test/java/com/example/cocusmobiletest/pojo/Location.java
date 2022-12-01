package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Location{
    @JsonProperty("street") 
    public Street getStreet() { 
		 return this.street; } 
    public void setStreet(Street street) { 
		 this.street = street; } 
    Street street;
    @JsonProperty("city") 
    public String getCity() { 
		 return this.city; } 
    public void setCity(String city) { 
		 this.city = city; } 
    String city;
    @JsonProperty("state") 
    public String getState() { 
		 return this.state; } 
    public void setState(String state) { 
		 this.state = state; } 
    String state;
    @JsonProperty("country") 
    public String getCountry() { 
		 return this.country; } 
    public void setCountry(String country) { 
		 this.country = country; } 
    String country;
    @JsonProperty("postcode") 
    public String getPostcode() { 
		 return this.postcode; } 
    public void setPostcode(String postcode) { 
		 this.postcode = postcode; } 
    String postcode;
    @JsonProperty("coordinates") 
    public Coordinates getCoordinates() { 
		 return this.coordinates; } 
    public void setCoordinates(Coordinates coordinates) { 
		 this.coordinates = coordinates; } 
    Coordinates coordinates;
    @JsonProperty("timezone") 
    public Timezone getTimezone() { 
		 return this.timezone; } 
    public void setTimezone(Timezone timezone) { 
		 this.timezone = timezone; } 
    Timezone timezone;
}
