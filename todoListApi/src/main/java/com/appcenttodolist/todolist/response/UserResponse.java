package com.appcenttodolist.todolist.response;

import com.appcenttodolist.todolist.entity.User;
import lombok.Data;

@Data
public class UserResponse {

    Long id;
    String userName;
    String email;

    public UserResponse(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.email = entity.getEmail();
    }

}
