package com.ratnesh.rest.webservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ratnesh.rest.webservice.helloworld.bean.HelloWorldBean;

@RestController
public class HelloWorldService {

	@GetMapping("/hello-world-get")
	public String helloWorldGet()
	{
		return "Hello world using @GetMapping";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World bean");
	}
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World %s",name));
	}
	
	@RequestMapping(method=RequestMethod.GET,path="/hello-world-request-mapping")
	public String helloWorldRequestMapping()
	{
		return "Hello world using @RequestMapping";
	}
	
	
}
