package com.todo.todoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todoapp.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
