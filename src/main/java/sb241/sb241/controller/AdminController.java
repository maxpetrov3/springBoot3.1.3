package sb241.sb241.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import sb241.sb241.model.Role;
import sb241.sb241.model.User;
import sb241.sb241.service.RoleService;
import sb241.sb241.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/adminPanel")
	public String getUsers(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User userDetails = (User) authentication.getPrincipal();
		String roles = userDetails.getAuthorities().stream()
				.map(x -> x.getRoleName().replace("ROLE_", ""))
				.collect(Collectors.joining(" "));
		model.addAttribute("user", userDetails);
		model.addAttribute("roles", roles);
		model.addAttribute("users", userService.getAllUsers());
		return "adminPage";
	}

	@ModelAttribute("allRoles")
	public Set<Role> populateVarieties() {
		return roleService.getAllRoles();
	}

	@ModelAttribute("newUser")
	public User newUser() {
		return new User();
	}

}