package com.appcenttodolist.todolist.controller;

import com.appcenttodolist.todolist.core.Results.DataResult;
import com.appcenttodolist.todolist.core.Results.Result;
import com.appcenttodolist.todolist.entity.User;
import com.appcenttodolist.todolist.service.abstracts.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    DataResult<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public Result createUser(@RequestBody User user){
        return userService.saveOneUser(user);
    }

    @GetMapping("/{userId}")
    public Result getOneUser(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User user){
        return userService.updateOneUser(userId,user);
    }

    @DeleteMapping("/{userId}")
    public Result delete(@RequestParam Long userId) {
        return this.userService.delete(userId);
    }

}
