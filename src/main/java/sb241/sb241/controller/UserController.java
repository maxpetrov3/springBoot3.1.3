package sb241.sb241.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sb241.sb241.model.Role;
import sb241.sb241.model.User;
import sb241.sb241.service.RoleService;
import sb241.sb241.service.UserService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userPanel")
    public String getUserInfo(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream()
                .map(x -> x.getRoleName().replace("ROLE_", ""))
                .collect(Collectors.joining(" "));
        model.addAttribute("user", userDetails);
        model.addAttribute("roles", roles);
        return "userInfo";
    }

    @PostMapping("/deleteteUser")
    public String deleteUser(@RequestParam("delId") Long id) {
        userService.deleteUserById(id);
        return "redirect:/adminPanel";
    }

    @PostMapping("/saveNewUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/adminPanel";
    }

    @PostMapping("/editUser")
    public String saveChanges(
            @ModelAttribute("id") Long id,
            @ModelAttribute("name") String name,
            @ModelAttribute("lastName") String lastname,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("age") Integer age,
            @ModelAttribute("roles") Set<Role> roles) {

        roles.addAll(userService.getUserById(id).getAuthorities());

        User user = new User(id, name, lastname, age, username, password, roles);
        userService.updateUser(user);
        return "redirect:/adminPanel";
    }
}
