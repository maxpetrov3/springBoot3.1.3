package sb241.sb241.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sb241.sb241.model.User;
import sb241.sb241.service.RoleService;

import java.util.Set;

@Controller
public class NewUserController {

    @PostMapping("/saveNewUser")
    public String getRiles(@ModelAttribute User user) {
        System.out.println(user.getAuthorities());
        return "adminPanel";
    }
}
