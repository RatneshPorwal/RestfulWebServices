package com.ratnesh.rest.webservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

import com.ratnesh.rest.webservice.user.bean.Address;
import com.ratnesh.rest.webservice.user.bean.User;
import com.ratnesh.rest.webservice.user.dao.UserServiceDAO;
import com.ratnesh.rest.webservice.user.exception.MissingMandatoryInformationException;
import com.ratnesh.rest.webservice.user.exception.UserNotFoundException;

@RestController
public class UserService {

	@Autowired
	private UserServiceDAO userDao;

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		
		User savedUser = userDao.findOne(id);
		
		if (savedUser==null)
			throw new UserNotFoundException("id-"+id);
		
		EntityModel<User> model = EntityModel.of(savedUser);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		model.add(linkTo.withRel("users-list"));
		
		return model;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void removeUser(@PathVariable int id) {
		User removedUser = userDao.deleteById(id);
		
		if (removedUser==null)
			throw new UserNotFoundException("id-"+id);
		
	}

	@PostMapping(path = "/users")
	public ResponseEntity saveUser(@Valid @RequestBody User user) {
		
		
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/users/{id}/addresses")
	public List<Address> getAddresses(@PathVariable int id) {
		
		return userDao.findAllAddress(id);
	}
	
	@PostMapping(path = "/users/{id}/addresses")
	public ResponseEntity saveAddress(@PathVariable int id,@RequestBody Address address) {
		
		if(address.getDistrict()==null)
		{
			throw new MissingMandatoryInformationException("MissingMandatoryInformationException ");
		}
		
		User user = userDao.saveAddress(id,address);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		/*
		 * URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		 * .path("/{id}") .buildAndExpand(id) .toUri();
		 */

		return ResponseEntity.created(location).build();
	}

}
