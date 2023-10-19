package com.user.userService.util;

public class customException extends RuntimeException{
	
	private String ecode;
	private String emessage;
	
	
	public customException(String ecode, String emessage) {
		super(emessage);
		this.ecode = ecode;
		this.emessage = emessage;
	}


	public String getEcode() {
		return ecode;
	}


	public void setEcode(String ecode) {
		this.ecode = ecode;
	}


	public String getEmessage() {
		return emessage;
	}


	public void setEmessage(String emessage) {
		this.emessage = emessage;
	}
	

}
