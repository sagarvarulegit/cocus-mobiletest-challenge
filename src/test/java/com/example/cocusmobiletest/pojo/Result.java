package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result{
    @JsonProperty("gender") 
    public String getGender() { 
		 return this.gender; } 
    public void setGender(String gender) { 
		 this.gender = gender; } 
    String gender;
    @JsonProperty("name") 
    public Name getName() { 
		 return this.name; } 
    public void setName(Name name) { 
		 this.name = name; } 
    Name name;
    @JsonProperty("location") 
    public Location getLocation() { 
		 return this.location; } 
    public void setLocation(Location location) { 
		 this.location = location; } 
    Location location;
    @JsonProperty("email") 
    public String getEmail() { 
		 return this.email; } 
    public void setEmail(String email) { 
		 this.email = email; } 
    String email;
    @JsonProperty("login") 
    public Login getLogin() { 
		 return this.login; } 
    public void setLogin(Login login) { 
		 this.login = login; } 
    Login login;
    @JsonProperty("dob") 
    public Dob getDob() { 
		 return this.dob; } 
    public void setDob(Dob dob) { 
		 this.dob = dob; } 
    Dob dob;
    @JsonProperty("registered") 
    public Registered getRegistered() { 
		 return this.registered; } 
    public void setRegistered(Registered registered) { 
		 this.registered = registered; } 
    Registered registered;
    @JsonProperty("phone") 
    public String getPhone() { 
		 return this.phone; } 
    public void setPhone(String phone) { 
		 this.phone = phone; } 
    String phone;
    @JsonProperty("cell") 
    public String getCell() { 
		 return this.cell; } 
    public void setCell(String cell) { 
		 this.cell = cell; } 
    String cell;
    @JsonProperty("id") 
    public Id getId() { 
		 return this.id; } 
    public void setId(Id id) { 
		 this.id = id; } 
    Id id;
    @JsonProperty("picture") 
    public Picture getPicture() { 
		 return this.picture; } 
    public void setPicture(Picture picture) { 
		 this.picture = picture; } 
    Picture picture;
    @JsonProperty("nat") 
    public String getNat() { 
		 return this.nat; } 
    public void setNat(String nat) { 
		 this.nat = nat; } 
    String nat;
}






