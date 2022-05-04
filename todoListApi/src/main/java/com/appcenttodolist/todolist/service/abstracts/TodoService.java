package com.appcenttodolist.todolist.service.abstracts;

import com.appcenttodolist.todolist.core.Results.DataResult;
import com.appcenttodolist.todolist.core.Results.Result;
import com.appcenttodolist.todolist.dto.TodoDto;
import com.appcenttodolist.todolist.entity.Todo;

import java.util.List;

public interface TodoService {

    Result addTodo(Todo todo); //v1
    Todo addTodoDto(TodoDto todo); //v2
    Result updateTodoDto(TodoDto todo);
    Todo updateOneTodo(Long todoId,Todo todo);
    Result delete(Long todoId);
    void delete(Todo todo);
    DataResult<List<Todo>> getAllTodo();
    List<TodoDto> getAllUsersTodos(Todo todo);
    Result getTodo(Long todoId);

    Result changeIsActiveByTodos(Long todoId);
    DataResult<List<Todo>> getOneByUser(Long id);
    DataResult<List<Todo>> getAllDescByCreatedDate();
    List<Todo> getByTodoNameContains(String todoName);
    List<Todo> getByTodoNameStartsWith(String todoName);
    DataResult<List<Todo>> getOneByUserName(String userName);


}
