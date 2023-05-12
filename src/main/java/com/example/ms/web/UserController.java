package com.example.ms.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@RestController
public class UserRestController {

	@Autowired
	private UserDaoService daoService;

	@GetMapping(path = "/users")
	public List<User> getAllUsers(){
		return daoService.getAllUsers();
	}

	@GetMapping(path = "/users/{id}")
	public User getUser(@PathVariable int id) throws UserNotFoundException {

		User user = daoService.getUser(id);
		if(user == null) {
			throw new UserNotFoundException("id: "+ id);
		}
		return user;
	}

	@Operation(summary = "Add a new User")
	@PostMapping(path = "/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) throws MalformedURLException {
		User savedUser = daoService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		daoService.deleteUser(id);
	}
}
