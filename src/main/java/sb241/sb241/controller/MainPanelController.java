package sb241.sb241.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import sb241.sb241.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Controller
public class MainPanelController {

    @GetMapping(value = "/mainPanel")
    public String getMainData(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().stream()
                .map(x -> x.getRoleName().replace("ROLE_", ""))
                .collect(Collectors.joining(" "));
        model.addAttribute("user", userDetails);
        model.addAttribute("roles", roles);
        return "mainPanel";
    }
}
