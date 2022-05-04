package com.appcenttodolist.todolist.service.concretes;

import com.appcenttodolist.todolist.core.Business.BusinessEngine;
import com.appcenttodolist.todolist.core.Results.*;
import com.appcenttodolist.todolist.entity.User;
import com.appcenttodolist.todolist.repository.UserRepository;
import com.appcenttodolist.todolist.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result saveOneUser(User user) {
        Result engine = BusinessEngine
                .run(isRealEmail(user),
                        emailNullChecker(user),
                        isMailRegistered(user));

        if(!engine.isSuccess()) {
            return new ErrorResult(engine.getMessage());
        }else{
            this.userRepository.save((user));
            return new SuccessResult("User added");
        }
    }

    @Override
    public Result deleteOneUser(User user) {
        this.userRepository.delete(user);
        return new SuccessResult("User deleted");
    }

    @Override
    public Result delete(Long userId) {
        this.userRepository.deleteById(userId);
        return new SuccessResult("User deleted for id");
    }

    @Override
    public User updateOneUser(Long userId, User user) {
        Optional<User> newUser = userRepository.findById(userId);
        if(newUser.isPresent()) {
            User foundUser = newUser.get();
            foundUser.setUserName(user.getUserName());
            foundUser.setEmail(user.getEmail());
            foundUser.setUserName(user.getPassword());
            this.userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }

    @Override
    public DataResult<List<User>> getAllUsers() {
        return new SuccessDataResult<List<User>>
                (this.userRepository.findAll(),"Users Listed");
    }

    @Override
    public Result getOneUserById(Long userId) {
        this.userRepository.findById(userId).orElse(null);
        return new SuccessResult("One User Listed for Id");
    }

    @Override
    public Result findByUserName(String userName) {
        this.userRepository.findByUserName(userName);
        return new SuccessResult("One User Listed for UserName");
    }

    @Override
    public Boolean existsByUserName(String username) {
        return this.userRepository.existsByUserName(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    private Result isRealEmail(User user) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if(!matcher.matches()) {
            return new ErrorResult("Not formatted email");
        }
        return new SuccessResult();
    }

    private Result emailNullChecker(User user) {
        if(user.getEmail().isEmpty() || user.getEmail().equals(null)) {
            return new ErrorResult("Email null");
        }
        return new SuccessResult();
    }

    private Result isMailRegistered(User user) {
        if(userRepository.findAllByEmail(user.getEmail()).stream().count() != 0) {
            return new ErrorResult("Already Registered Mail");
        }
        return new SuccessResult();
    }

}
