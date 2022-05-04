package com.appcenttodolist.todolist.repository;

import com.appcenttodolist.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAllByEmail(String email);
    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);

}
