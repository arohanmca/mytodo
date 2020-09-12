package com.todo.mytodo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.mytodo.user.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
