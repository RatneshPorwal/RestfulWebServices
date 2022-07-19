package com.ratnesh.rest.webservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.ratnesh.rest.webservice.user.bean.MyAddress;
import com.ratnesh.rest.webservice.user.bean.MyUser;
import com.ratnesh.rest.webservice.user.bean.User;
import com.ratnesh.rest.webservice.user.dao.AddressRepository;
import com.ratnesh.rest.webservice.user.dao.UserRepository;
import com.ratnesh.rest.webservice.user.dao.UserServiceDAO;
import com.ratnesh.rest.webservice.user.exception.MissingMandatoryInformationException;
import com.ratnesh.rest.webservice.user.exception.UserNotFoundException;

@RestController
public class UserJpaService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserServiceDAO userDao;

	@GetMapping(path = "/jpa/users")
	public List<MyUser> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<Optional<MyUser>> getUser(@PathVariable int id) {
		
		Optional<MyUser> savedUser = userRepository.findById(id);
		
		if (savedUser==null)
			throw new UserNotFoundException("id-"+id);
		
		EntityModel<Optional<MyUser>> model = EntityModel.of(savedUser);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getUsers());
		
		model.add(linkTo.withRel("users-list"));
		
		return model;
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void removeUser(@PathVariable int id) {
		 userRepository.deleteById(id);
	
		
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity saveUser(@Valid @RequestBody MyUser user) {
		
		
		MyUser savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/jpa/users/{id}/addresses")
	public List<MyAddress> getAddresses(@PathVariable int id) {
		Optional<MyUser> savedUser = userRepository.findById(id);
		
		if(!savedUser.isPresent())
			throw new UserNotFoundException("id-"+id);
			
		
		return savedUser.get().getAddresses();
	}
	
	@PostMapping(path = "/jpa/users/{id}/addresses")
	public ResponseEntity saveAddress(@PathVariable int id,@RequestBody MyAddress address) {

		Optional<MyUser> savedUser = userRepository.findById(id);
		
		if(!savedUser.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		MyUser myUser=savedUser.get();
		
		address.setMyUser(myUser);
		
		addressRepository.save(address);
		
		//URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
		  .path("/{id}") .buildAndExpand(address.getId()) .toUri();
		 

		return ResponseEntity.created(location).build();
	}

}
