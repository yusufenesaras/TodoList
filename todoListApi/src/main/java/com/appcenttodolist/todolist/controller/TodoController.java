package com.appcenttodolist.todolist.controller;

import com.appcenttodolist.todolist.core.Results.DataResult;
import com.appcenttodolist.todolist.core.Results.Result;
import com.appcenttodolist.todolist.dto.TodoDto;
import com.appcenttodolist.todolist.entity.Todo;
import com.appcenttodolist.todolist.service.abstracts.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/add")
    Result addTodo(@RequestBody Todo todo){
        return this.todoService.addTodo(todo);
    }

    @PostMapping("/v2/addDto")
    public Todo add(@RequestBody TodoDto todo){
        return this.todoService.addTodoDto(todo);
    }

    @GetMapping("/getAll")
    public DataResult<List<Todo>> getAll(){
        return this.todoService.getAllTodo();
    }

    @GetMapping("/getAllUsersDto")
    public List<TodoDto> getAllUsersTodos(Todo todo){
        return this.todoService.getAllUsersTodos(todo);
    }

    @DeleteMapping("{todoId}")
    Result delete(@RequestParam Long todoId){
        return this.todoService.delete(todoId);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Todo todo){
         this.todoService.delete(todo);
    }

    @PutMapping("/{todoId}")
    Todo updateOneTodo(@PathVariable Long todoId, @RequestBody Todo todo){
        return todoService.updateOneTodo(todoId,todo);
    }

    @PutMapping("/updateTodoDto")
    public ResponseEntity<?> updateTodoDto(@Valid @RequestBody TodoDto todo){
        return ResponseEntity.ok(this.todoService.updateTodoDto(todo));
    }

    @GetMapping("/getOneByUserName")
    DataResult<List<Todo>> getOneByUserName(String userName){
        return this.todoService.getOneByUserName(userName);
    }

    @GetMapping("/getTodo")
    Result getTodo(@RequestParam Long todoId){
        return this.todoService.getTodo(todoId);
    }

    @GetMapping("/getOneByUser")
    DataResult<List<Todo>> getOneByUser(@RequestParam Long id){
        return this.todoService.getOneByUser(id);
    }

    @GetMapping("/getByTodoNameContains")
    List<Todo> getByTodoNameContains(String todoName){
        return this.todoService.getByTodoNameContains(todoName);
    }

    @GetMapping("/getByTodoNameStartsWith")
    List<Todo> getByTodoNameStartsWith(String todoName){
        return this.todoService.getByTodoNameStartsWith(todoName);
    }

    @PostMapping("/changeActiveStatus")
    Result changeIsActiveByTodos(Long todoId){
        return this.todoService.changeIsActiveByTodos(todoId);
    }

    @GetMapping("/getAllDescByCreatedDate")
    public DataResult<List<Todo>> getAllDescByCreatedDate(){
        return this.todoService.getAllDescByCreatedDate();
    }
}
