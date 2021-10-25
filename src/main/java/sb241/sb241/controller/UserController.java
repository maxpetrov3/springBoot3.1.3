package sb241.sb241.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import sb241.sb241.model.User;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping(value = "/user")
    private String getUserInfo(HttpServletRequest request, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        model.addAttribute("user", userDetails);
        return "user";
    }
}
