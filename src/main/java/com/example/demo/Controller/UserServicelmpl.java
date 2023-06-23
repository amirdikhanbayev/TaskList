package com.example.demo.Controller;

import com.example.demo.Repository.UserService;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserServicelmpl {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User AddUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping("/user")
    public List<User> getAll(){return userService.findAll();}

    @GetMapping("/user/{id}")
    public User FindById(@PathVariable Long id){
        return userService.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user")
    public User patchPassword(@RequestBody User user) throws EntityNotFoundException{
        User old1 = userService.findById(user.getId()).orElse(new User());
        old1.setId(user.getId());
        Optional.ofNullable(user.getLogin()).ifPresent(old1::setLogin);
        Optional.ofNullable(user.getPassword()).ifPresent(old1::setPassword);
        return userService.save(old1);
    }
}
