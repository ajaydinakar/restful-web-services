package com.ajayjava.rest.webservices.restfulwebservices.versioning;

public class Name {
public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
private String firstName;
public Name() {
	super();
}
public Name(String firstName, String secondName) {
	super();
	this.firstName = firstName;
	this.secondName = secondName;
}
private String secondName;
}
