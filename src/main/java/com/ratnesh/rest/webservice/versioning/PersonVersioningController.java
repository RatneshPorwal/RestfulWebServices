package com.ratnesh.rest.webservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	@GetMapping("/person/v1/")
	public PersonV1 getPersonV1()
	{
		return new PersonV1("Ratnesh Porwal");
	}
	
	@GetMapping("/person/v2/")
	public PersonV2 getPersonV2()
	{
		return new PersonV2(new Name("Ratnesh","Porwal"));
	}
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 getPersonV11()
	{
		return new PersonV1("Ratnesh Porwal");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 getPersonV22()
	{
		return new PersonV2(new Name("Ratnesh","Porwal"));
	}

	
	@GetMapping(value="/person/header",headers="version=1")
	public PersonV1 getheaderV11()
	{
		return new PersonV1("Ratnesh Porwal");
	}
	
	@GetMapping(value="/person/header",headers="version=2")
	public PersonV2 getheaderV22()
	{
		return new PersonV2(new Name("Ratnesh","Porwal"));
	}
}
