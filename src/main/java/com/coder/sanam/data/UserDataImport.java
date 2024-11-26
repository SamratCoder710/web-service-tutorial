package com.coder.sanam.data;


import com.coder.sanam.model.User;
import com.coder.sanam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataImport implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        System.out.println("Importing Users...");
        List<User> users = new ArrayList<>();
        users.add(new User("Sanam", LocalDate.now().minusYears(24)));
        users.add(new User("Aditi", LocalDate.now().minusYears(25)));
        users.add(new User("Shraddha", LocalDate.now().minusYears(25)));
        userRepository.saveAll(users);
    }
}
