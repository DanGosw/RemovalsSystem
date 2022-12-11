package com.dannyboy.removalssystem2;

import android.graphics.Bitmap;

public class ModelClass {
private String user, last, mail, pass;
private Bitmap img;

public ModelClass(String user, String last, String mail, String pass, Bitmap img) {
	
	this.user = user;
	this.last = last;
	this.mail = mail;
	this.pass = pass;
	this.img = img;
}

	public String getUser() {
		
		return user;
	}
	
	public void setUser(String user) {
		
		this.user = user;
	}
	public String getLast() {
		
		return last;
	}
	
	public void setLast(String last) {
		
		this.last = last;
	}
	
	public String getMail() {
		
		return mail;
	}
	
	public void setMail(String mail) {
		
		this.mail = mail;
	}
	
	public String getPass() {
		
		return pass;
	}
	
	public void setPass(String pass) {
		
		this.pass = pass;
	}
	
	public Bitmap getImg() {
		return img;
	}
	
	public void setImg(Bitmap img) {
		
		this.img = img;
	}
}