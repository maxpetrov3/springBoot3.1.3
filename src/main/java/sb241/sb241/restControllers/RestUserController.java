package sb241.sb241.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sb241.sb241.model.User;
import sb241.sb241.repositories.UserRepository;

import java.util.List;

@CrossOrigin
@RestController
public class RestUserController {


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam Long id) {

        return userRepository.findById(id).get();
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @PostMapping("/deleteUser")
    public void deleteUser(@RequestBody Long id) {
        userRepository.deleteById(id);
    }
}
