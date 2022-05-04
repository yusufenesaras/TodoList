package com.appcenttodolist.todolist.service.abstracts;

import com.appcenttodolist.todolist.core.Results.DataResult;
import com.appcenttodolist.todolist.core.Results.Result;
import com.appcenttodolist.todolist.entity.User;
import com.appcenttodolist.todolist.repository.UserRepository;

import java.util.List;

public interface UserService {

     Result saveOneUser(User user);
     Result deleteOneUser(User user);
     Result delete(Long userId);
     User updateOneUser(Long userId, User user);


     DataResult<List<User>> getAllUsers();
     Result getOneUserById(Long userId);
     Result findByUserName(String userName);

     Boolean existsByUserName(String username);
     Boolean existsByEmail(String email);


}
