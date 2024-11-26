package com.coder.sanam.dao;

import com.coder.sanam.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final List<User> users = new ArrayList<>();

    private static int userId = 0;

    static{
        users.add(new User(++userId,"Sanam", LocalDate.now().minusYears(24)));
        users.add(new User(++userId,"Aditi", LocalDate.now().minusYears(25)));
        users.add(new User(++userId,"Shraddha", LocalDate.now().minusYears(25)));
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User findOne(int id){
        return users.stream().filter(user1 -> user1.getId().equals(id)).findFirst().orElse(null);
    }

    public ResponseEntity<Object> createUser(User user) {
        user.setId(++userId);
        users.add(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
        return ResponseEntity.created(location).build();
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}

