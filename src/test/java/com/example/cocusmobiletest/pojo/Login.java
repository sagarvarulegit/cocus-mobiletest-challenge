package com.example.cocusmobiletest.pojo;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Login{
    @JsonProperty("uuid") 
    public String getUuid() { 
		 return this.uuid; } 
    public void setUuid(String uuid) { 
		 this.uuid = uuid; } 
    String uuid;
    @JsonProperty("username") 
    public String getUsername() { 
		 return this.username; } 
    public void setUsername(String username) { 
		 this.username = username; } 
    String username;
    @JsonProperty("password") 
    public String getPassword() { 
		 return this.password; } 
    public void setPassword(String password) { 
		 this.password = password; } 
    String password;
    @JsonProperty("salt") 
    public String getSalt() { 
		 return this.salt; } 
    public void setSalt(String salt) { 
		 this.salt = salt; } 
    String salt;
    @JsonProperty("md5") 
    public String getMd5() { 
		 return this.md5; } 
    public void setMd5(String md5) { 
		 this.md5 = md5; } 
    String md5;
    @JsonProperty("sha1") 
    public String getSha1() { 
		 return this.sha1; } 
    public void setSha1(String sha1) { 
		 this.sha1 = sha1; } 
    String sha1;
    @JsonProperty("sha256") 
    public String getSha256() { 
		 return this.sha256; } 
    public void setSha256(String sha256) { 
		 this.sha256 = sha256; } 
    String sha256;
}
