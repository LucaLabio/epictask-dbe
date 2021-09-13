package br.com.fiap.epictask.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Signup;
import br.com.fiap.epictask.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<Signup> users = repository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping("/login/signup")
	public String create(Signup signup) {
		return "signup";
	}
	
	@PostMapping("/users")
	public String save(@Valid Signup signup, BindingResult result) {
		if (result.hasErrors()) return "signup";
		repository.save(signup);
		return "users";
	}

}
