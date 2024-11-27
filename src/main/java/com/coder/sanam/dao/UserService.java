package com.coder.sanam.dao;

import com.coder.sanam.exception.UserNotFoundException;
import com.coder.sanam.model.Post;
import com.coder.sanam.model.User;
import com.coder.sanam.repository.PostRepository;
import com.coder.sanam.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll().stream().sorted(Comparator.comparingInt(User::getId)).toList();
    }

    public User findOne(int id){
        return userRepository.findAll().stream().filter(user1 -> user1.getId().equals(id)).findFirst().orElse(null);
    }

    public ResponseEntity<Object> createUser(User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public void deleteUser(int id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            throw new UserNotFoundException("User not found : Id "+id);
        }
        userRepository.delete(byId.get());
    }

    public ResponseEntity<Object> updateUser(int userId, @Valid User userToBeUpdated) {
        User existingUser = findOne(userId);
        if(existingUser == null) throw new UserNotFoundException("User not found :Id "+userId);

        existingUser.setName(userToBeUpdated.getName());
        existingUser.setBirthDate(userToBeUpdated.getBirthDate());
        User updatedUser = userRepository.save(existingUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    public ResponseEntity<Object> getAllPostOfaUser(int userId) {
        User existingUser = findOne(userId);
        if(existingUser == null) throw new UserNotFoundException("User not found :Id "+userId);
        return new ResponseEntity<>(existingUser.getPosts(),HttpStatus.OK);
    }

    public ResponseEntity<Object> savePostOfaUser(int userId, Post post) {
        User existingUser = findOne(userId);
        if(existingUser == null) throw new UserNotFoundException("User not found :Id "+userId);
        post.setUser(existingUser);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}

