package com.ajayjava.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ajayjava.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
//retrive all users
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers()
	{
		
		return service.findAll();
		
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User>retriveUser(@PathVariable int id)
	{
		User user=service.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
			
		}
		EntityModel<User> model=EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers =linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUsers.withRel("all-Users"));
		return model;
		
	}
	@DeleteMapping(path="/users/{id}")
	public User deleteUser(@PathVariable int id)
	{
		User user=service.deletebyId(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		return user;
		
	}
	@PostMapping("/users")
	public ResponseEntity<Object>  createUser( @Valid @RequestBody User user)
	{
		User saveUser=service.save(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
	return	ResponseEntity.created(location).build();
	}
}
