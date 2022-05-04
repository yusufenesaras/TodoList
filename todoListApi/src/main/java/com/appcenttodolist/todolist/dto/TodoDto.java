package com.appcenttodolist.todolist.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long todoId;
    private String todoName;
    private String todoDescription;
    private Long userId;

}
