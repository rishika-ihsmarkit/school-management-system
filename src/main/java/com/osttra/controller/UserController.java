package com.osttra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.osttra.service.UserService;
import com.osttra.to.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String register(User user) {
		userService.register(user);
		return "index";
		
	}
	
	
	@PostMapping("/login")
	public ModelAndView login(String userid, String password, HttpServletRequest request) {
		
		User user = userService.login(userid, password);
		ModelAndView modelAndView = null;
		
		if( user != null ) {
			if(user.getStatus().equals("active")) {
				
				HttpSession session = request.getSession();

				if(user.getRole().equals("admin")) {
					List<User> userList =  userService.allUsers();
					session.setAttribute("userList", userList);
				}
				modelAndView = new ModelAndView("welcome_page");
				session.setAttribute("user", user);
				
			}else {
				modelAndView = new ModelAndView("index");
				modelAndView.addObject("errorMessage", "User not active !!");
			}
		}else {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "Wrong Credentials, please try again!!");
		}
		return modelAndView;
	}
	
	
	
	@GetMapping("/delete/{userid}")
	public ModelAndView delete(@PathVariable String userid, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		ModelAndView modelAndView = new ModelAndView("index");
		
		if( session != null ) {
			userService.delete(userid);
		}
		else {
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}
		
		return modelAndView;
		
	}
	
	@GetMapping("/deleteUser/{userid}")
	public ModelAndView deleteUser(@PathVariable String userid, HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		
		if( session != null ) {
			
			userService.delete(userid);
			
			modelAndView = new ModelAndView("welcome_page");
			List<User> userList =  userService.allUsers();
			session.setAttribute("userList", userList);
			
		}
		else {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}
		
		return modelAndView;
		
	}
	
	@GetMapping("/logout/{userid}")
	public ModelAndView logout(@PathVariable String userid, HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		
		modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("Message", "Logged out successfully!!");
		
		HttpSession session = request.getSession(false);
		
		if( session != null ) {
			System.out.println(session);
			session.invalidate();
		}
		else {
			modelAndView = new ModelAndView("index");
		}
		
		return modelAndView;
		
	}
	
	@GetMapping("/updatePage/{userid}")
	public ModelAndView updatePage(@PathVariable String userid, HttpServletRequest request) {

		ModelAndView modelAndView = null;
		
		HttpSession session = request.getSession(false);
		
		User deletingUser = userService.getUser(userid);

		if( session != null ) {

			modelAndView = new ModelAndView("update_user");
			modelAndView.addObject("deletingUser", deletingUser);
		}
		else {
			modelAndView = new ModelAndView("index");
		}
		
		return modelAndView;
			
	}
	
	
	@PostMapping("/update")
	public ModelAndView update(User user, HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		
		User oldUser = userService.getUser(user.getUserid());
		
		String password = oldUser.getPassword();
		String role = oldUser.getRole();
		String status = oldUser.getStatus();
		String userid = oldUser.getUserid();
		
		user.setPassword(password);   user.setRole(role);	user.setStatus(status);	user.setUserid(userid);
		
		session.setAttribute("user", user);
		
		userService.update(user);
		
		ModelAndView modelAndView = null;
		
		modelAndView = new ModelAndView("welcome_page");
		
		modelAndView.addObject("updateSuccessMsg", "Profile is updated successfully");
		
		return modelAndView;
	
	}
	
	@GetMapping("/block/{userid}")
	public ModelAndView block(@PathVariable String userid, HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		
		if( session != null ) {
			userService.blockUser(userid);
			
			List<User> userList =  userService.allUsers();
			session.setAttribute("userList", userList);
			
			modelAndView = new ModelAndView("welcome_page");
		}
		else {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}
		
		
		return modelAndView;
		
	}
	
	@GetMapping("/activate/{userid}")
	public ModelAndView activate(@PathVariable String userid, HttpServletRequest request) {
		
		ModelAndView modelAndView = null;
		HttpSession session = request.getSession(false);
		
		if( session != null ) {
			userService.activateUser(userid);
			
			List<User> userList =  userService.allUsers();
			session.setAttribute("userList", userList);
			
			modelAndView = new ModelAndView("welcome_page");
		}
		else {
			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}
		
		return modelAndView;
		
	}
	
	@GetMapping("/home")
	public ModelAndView welcomeHome(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		ModelAndView modelAndView = null;

		if (session != null) {

			if (((User) session.getAttribute("user")).getRole().equals("Admin")) {

				modelAndView = new ModelAndView("welcome_page");
				List<User> userList = userService.allUsers();
				session.setAttribute("userList", userList);
			} else {

				modelAndView = new ModelAndView("welcome_page");
			}

		} else {

			modelAndView = new ModelAndView("index");
			modelAndView.addObject("errorMessage", "You are not authenticated, please login first!!");
		}
		return modelAndView;
	}

}