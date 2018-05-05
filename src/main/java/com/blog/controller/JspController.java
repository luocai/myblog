package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

	@RequestMapping("/showImg")
	public String showImg(){
		
		return "showImg";
	}
}
