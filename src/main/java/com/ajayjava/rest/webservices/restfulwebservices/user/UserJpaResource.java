package com.ajayjava.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ajayjava.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
@RequestMapping("/jpa")
public class UserJpaResource {

	@Autowired
	private UserDaoService service;
	@Autowired
	private UserRepository repository;
	@Autowired
	private PostRepository postRepository;
//retrive all users
	
	@GetMapping(path="/users")
	public List<User> retriveAllUsers()
	{
		
		return repository.findAll();
		
	}
	
	@GetMapping(path="/users/{id}")
	public EntityModel<User>retriveUser(@PathVariable int id)
	{
		Optional<User> user=repository.findById(id);
		if(!(user.isPresent()))
		{
			throw new UserNotFoundException("id-"+id);
			
		}
		EntityModel<User> model=EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers =linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUsers.withRel("all-Users"));
		return model;
		
	}
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		repository.deleteById(id);
		
		
	}
	@PostMapping("/users")
	public ResponseEntity<Object>  createUser( @Valid @RequestBody User user)
	{
		User saveUser=repository.save(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
	return	ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/users/{id}/posts")
	public List<Post> retriveAllPosts(@PathVariable int id)
	{
	Optional<User>  userOptionlal=repository.findById(id);
	if (!userOptionlal.isPresent())
	{
		throw new UserNotFoundException("id-"+id);
	}
	
	return userOptionlal.get().getPosts();
	}
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object>  createPost(@PathVariable int id, @Valid @RequestBody Post post)
	{
		
		Optional<User>  userOptionlal=repository.findById(id);
		if (!userOptionlal.isPresent())
		{
			throw new UserNotFoundException("id-"+id);
		}
		User userpo=userOptionlal.get();
		post.setUser(userpo);
		postRepository.save(post);
		
		
		URI location =ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(post.getId()).toUri();
	return	ResponseEntity.created(location).build();
	}
}
