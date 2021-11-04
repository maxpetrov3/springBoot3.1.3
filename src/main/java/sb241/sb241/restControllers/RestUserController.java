package sb241.sb241.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb241.sb241.model.User;
import sb241.sb241.repositories.UserRepository;

import java.util.List;

@CrossOrigin
@RestController("/api/users")
public class RestUserController {


    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List> getAllUsers() {

        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/getUser")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {

        return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody Long id) {
        userRepository.deleteById(id);
    }
}
