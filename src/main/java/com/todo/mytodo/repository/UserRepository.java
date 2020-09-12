package com.todo.mytodo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.mytodo.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
