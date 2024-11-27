package com.coder.sanam.data;


import com.coder.sanam.model.Post;
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
        User sanamUser = User.builder().name("Sanam").birthDate(LocalDate.now().minusYears(24)).build();
        sanamUser.setPosts(List.of(
                Post.builder().description("Sanam teri kasam").user(sanamUser).build(),
                Post.builder().description("Ghajni").user(sanamUser).build()));
        users.add(sanamUser);

        User aditiUser = User.builder().name("Aditi").birthDate(LocalDate.now().minusYears(25)).build();
        aditiUser.setPosts(List.of(
                Post.builder().description("Mismatched").user(aditiUser).build(),
                Post.builder().description("To Jhooti mai makhaar").user(aditiUser).build()));
        users.add(aditiUser);

        User shraddhaUser = User.builder().name("Shraddha").birthDate(LocalDate.now().minusYears(25)).build();
        shraddhaUser.setPosts(List.of(
                Post.builder().description("Bramastra").user(shraddhaUser).build(),
                Post.builder().description("ZNMD").user(shraddhaUser).build()));
        users.add(shraddhaUser);

        userRepository.saveAll(users);
    }
}
