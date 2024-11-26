package com.coder.sanam.controller;

import com.coder.sanam.dao.UserService;
import com.coder.sanam.exception.UserNotFoundException;
import com.coder.sanam.model.User;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable int userId){
        User foundUser = userService.findOne(userId);
        if(foundUser == null) {
            throw new UserNotFoundException("id:"+userId);
        }

        return foundUser;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable int userId){
        userService.deleteUser(userId);
    }


}
