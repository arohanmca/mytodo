package com.todo.mytodo.controller;

import java.net.URI;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.mytodo.exception.UserNotFoundException;
import com.todo.mytodo.user.User;
import com.todo.mytodo.user.UserDaoService;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService userDaoService;
	
	// GET/users
	// retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll(); 
	}
	
	// GET /user/{id}
	// retrieveUser(int id)
	/*@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User findOneUser = userDaoService.findOne(id);
		// HATEOAS - import Hateoas Resouce for this 
		if(findOneUser == null) {
			throw new UserNotFoundException("id - "+id);
		} 
		return findOneUser;
	}*/
	
	// HATEOAS - Hypermedia As The Engine Of Application State
//	@GetMapping("/users/{id}")
//	public EntityModel<User> retrieveUser(@PathVariable int id) {
//		User findOneUser = userDaoService.findOne(id);
//		// HATEOAS - import Hateoas Resouce for this 
//		if(findOneUser == null) {
//			throw new UserNotFoundException("id - "+id);
//		}
//		EntityModel<User> resource = new EntityModel<User>(findOneUser); 
//		Link link = linkTo(methodOn(UserController.class)
//				.retrieveAllUsers())
//				.withRel("All users");
//		resource.add(link);
//		return resource;
//	}
	
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User findOneUser = userDaoService.findOne(id);
		// HATEOAS - import Hateoas Resouce for this 
		if(findOneUser == null) {
			throw new UserNotFoundException("id - "+id);
		} 
		Resource<User> resource = new Resource<User>(findOneUser);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("All Users"));
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		 
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User deletedUser = userDaoService.deleteById(id);
		if(deletedUser == null) {
			throw new UserNotFoundException("id - "+id);
		}
	}
	
}
