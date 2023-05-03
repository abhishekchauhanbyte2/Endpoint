package com.devops.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestClass {
	
	@GetMapping("/path")
	public String call() {
		return "Welcome to your application";
	}
}
