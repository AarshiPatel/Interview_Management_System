package com.ims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping(value = "Hello")
	public String loginpage() {
		return "index";
	}
}
