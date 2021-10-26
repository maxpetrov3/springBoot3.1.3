package sb241.sb241.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import sb241.sb241.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @GetMapping(value = "/userPanel")
    private String getUserInfo(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream()
                .map(x -> x.getRoleName().replace("ROLE_", ""))
                .collect(Collectors.joining(" "));
        model.addAttribute("user", userDetails);
        model.addAttribute("roles", roles);
        return "userPanel";
    }
}
