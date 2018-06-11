package com.blog.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.pojo.User;
import com.blog.service.impl.UserServiceImpl;

@Controller
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login(){
		
		return "login";
	}
	
	
	@RequestMapping(value = "/loginCheck", method = {RequestMethod.POST})
	public @ResponseBody Object loginCheck(HttpServletRequest request,HttpServletResponse response){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
System.out.println(username);
System.out.println(password);
		User user = userService.selectByUsername(username);
		HashMap<String, String> res = new HashMap<String, String>();
		if (user == null){
			res.put("stateCode", "0");
System.out.println(0);
		}else if(!password.equals(user.getPassword())){
			res.put("stateCode", "1");
System.out.println(1);
		}else{
			request.getSession().setAttribute("user",user);
			res.put("stateCode", "2");
System.out.println(2);
		}
System.out.println("hehe");
		return res;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		
		request.getSession().setAttribute("user", null);
		return "redirect:login";
	}
}
