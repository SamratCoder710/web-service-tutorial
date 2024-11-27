package com.coder.sanam.repository;

import com.coder.sanam.model.Post;
import com.coder.sanam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
