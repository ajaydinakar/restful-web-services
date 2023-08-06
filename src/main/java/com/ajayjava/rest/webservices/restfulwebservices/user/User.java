package com.ajayjava.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value= {"birthDate"})
@Entity
public class User {
	
	public User() {
		super();
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Id
	@GeneratedValue
private Integer id;
@Size(min=2,message="name should have atleast two characters")
private String name;

@OneToMany(mappedBy="user")
private List<Post> posts;



public User(Integer id, @Size(min = 2, message = "name should have atleast two characters") String name,
		List<Post> posts, @Past Date birthDate) {
	super();
	this.id = id;
	this.name = name;
	this.posts = posts;
	this.birthDate = birthDate;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getBirthDate() {
	return birthDate;
}
public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
}

@Past 
private Date birthDate;
}
