package com.ratnesh.rest.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldService {

	@GetMapping("/hello-world-get")
	public String helloWorldGet()
	{
		return "Hello world using @GetMapping";
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world-request-mapping")
	public String helloWorldRequestMapping()
	{
		return "Hello world using @RequestMapping";
	}
}
