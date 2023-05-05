package com.devops.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClass {
	
	@GetMapping("/path")
	public String call() {
		return "Welcome to your application";
	}
}
