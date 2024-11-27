package com.coder.sanam.controller;

import com.coder.sanam.dao.UserService;
import com.coder.sanam.exception.UserNotFoundException;
import com.coder.sanam.model.Post;
import com.coder.sanam.model.User;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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


    @GetMapping(value = "/users" )
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId){
        User foundUser = userService.findOne(userId);
        if(foundUser == null) {
            throw new UserNotFoundException("id:"+userId);
        }

        EntityModel<User> userEntityModel = EntityModel.of(foundUser);
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(webMvcLinkBuilder.withRel("all-users"));
        userEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(userId)).withSelfRel());
        return userEntityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody User userToBeUpdated, @PathVariable int userId){
        return userService.updateUser(userId,userToBeUpdated);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable int userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<Object> getAllPostOfaUser(@PathVariable int userId){
        return userService.getAllPostOfaUser(userId);
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> savePostOfaUser(@PathVariable int userId, @RequestBody Post post){
        return userService.savePostOfaUser(userId,post);
    }




}
