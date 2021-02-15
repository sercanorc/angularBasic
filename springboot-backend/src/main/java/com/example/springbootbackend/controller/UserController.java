package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.SercanEx;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }
    @PostMapping("users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("users/{id}")
    public User getUserById(@PathVariable long id){
        User user =userRepository.findById(id).orElseThrow(() -> new SercanEx("hata:"+id));
        return user;
    }
    @PutMapping("users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user =userRepository.findById(id).orElseThrow(() -> new SercanEx("hata:"+id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        User updateUser = userRepository.save(user);
        return updateUser;
    }
    @DeleteMapping("users/{id}")
    public void delete(@PathVariable Long id) {
        if(userRepository.findById(id)!= null) {
            userRepository.deleteById(id);
        }
    }
}
