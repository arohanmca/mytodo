package com.todo.todoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todoapp.user.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
