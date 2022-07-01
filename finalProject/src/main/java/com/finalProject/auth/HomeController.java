package com.finalProject.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	@SuppressWarnings("unused")
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MySQLCustomerDetailsService userService;
	
    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/secure")
    public String getSecurePage() {
        return "secure";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public String getRegisterPage() {
    	return "register";
    }
    @PostMapping("/register")
    public String createUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
    	Customer foundUser = customerRepository.findByUsername(username);
    	if (foundUser == null) {
    		Customer newUser = new User();
    		newUser.setUsername(username);
    		newUser.setPassword(password);
    		userService.Save(newUser);
    		return "login";
    	}
    	else {
    		model.addAttribute("exists", true);
    		return "register";
    	}
    }
}
