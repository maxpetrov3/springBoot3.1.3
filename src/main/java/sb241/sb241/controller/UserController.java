package sb241.sb241.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sb241.sb241.model.User;
import sb241.sb241.service.UserService;

import javax.servlet.http.HttpServletRequest;
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
        return "userPanel";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("tuser") User user) {
            userService.deleteUserById(user.getId());
        return "redirect:/adminPage";
    }

    @PostMapping("/saveNewUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/adminPanel";
    }
}
