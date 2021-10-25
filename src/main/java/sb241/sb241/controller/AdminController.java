package sb241.sb241.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sb241.sb241.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/admin")
	public String getUsers(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}

	@PostMapping(value = "/delete")
	public String deleteUser(@RequestParam(name = "delId") Long id) {
		userService.deleteUserById(id);
		return "redirect:/admin";
	}

}