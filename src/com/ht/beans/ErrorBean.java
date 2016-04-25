package com.ht.beans;

public class ErrorBean {
	private String errorMessage;

	public ErrorBean(String errorMessage){
		this.errorMessage=errorMessage;
	}
	
	public ErrorBean(){
		
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
