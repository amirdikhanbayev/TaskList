package com.example.demo.Controller;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    // user/add

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User AddUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/get")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/login/{login}")
    public User FindByLogin(@PathVariable String login) {
        return (User) userRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/put")
    public User patchPassword(@RequestBody User user) throws EntityNotFoundException {
        User old1 = userRepository.findById(user.getId()).orElse(new User());
        old1.setId(user.getId());
        Optional.ofNullable(user.getLogin()).ifPresent(old1::setLogin);
        Optional.ofNullable(user.getPassword()).ifPresent(old1::setPassword);
        return userRepository.save(old1);
    }

    @GetMapping("/me")
    public User me(){
        return userService.getCurrentUser();
    }
}
