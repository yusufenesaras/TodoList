package com.appcenttodolist.todolist.service.concretes;

import com.appcenttodolist.todolist.core.Business.BusinessEngine;
import com.appcenttodolist.todolist.core.ModelMapperDto.DtoService;
import com.appcenttodolist.todolist.core.Results.*;
import com.appcenttodolist.todolist.dto.TodoDto;
import com.appcenttodolist.todolist.entity.Todo;
import com.appcenttodolist.todolist.repository.TodoRepository;
import com.appcenttodolist.todolist.service.abstracts.TodoService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoManager implements TodoService {

    private final TodoRepository todoRepository;
    private final DtoService dtoService;

    public TodoManager(TodoRepository todoRepository
            ,DtoService dtoService) {
        this.todoRepository = todoRepository;
        this.dtoService = dtoService;

    }

    public Result addTodo(Todo todo) {
        this.todoRepository.save(todo);
        return new SuccessResult("Todo added");
    }

    @Override
    public Todo addTodoDto(TodoDto todo) {

        Result engine = BusinessEngine
                .run(todoNameNullChecker(todo),
                todoDescriptionNullChecker(todo));

        if(!engine.isSuccess()) {
        //    return new ErrorResult(engine.getMessage());
        }else{
          return this.todoRepository.save((Todo)
                    dtoService.dtoClassConverter(todo, Todo.class));
          //  return new SuccessResult("Todo added");
        }
        return null;
    }

    @Override
    public Todo updateOneTodo(Long todoId, Todo todo) {
        Optional<Todo> newTodo = todoRepository.findById(todoId);

        if(newTodo.isPresent()) {
            Todo foundTodo = newTodo.get();
            foundTodo.setTodoName(todo.getTodoName());
            foundTodo.setTodoDescription(todo.getTodoDescription());
            foundTodo.setCreatedDate(todo.getCreatedDate());
            foundTodo.setComplete(todo.isComplete());
            this.todoRepository.save(foundTodo);
            return foundTodo;

        }else{
            return null;
        }
    }

    @Override
    public Result updateTodoDto(TodoDto todo) {

        Todo ref = todoRepository.getById(todo.getTodoId());

        if(todo.getTodoName() != null) {
            ref.setTodoName(todo.getTodoName());
        }
        if(todo.getTodoDescription() != null) {
            ref.setTodoDescription(todo.getTodoDescription());
        }

        this.todoRepository.save((Todo)
                dtoService.dtoClassConverter(ref, Todo.class));

        return new SuccessResult("Update Success");
    }

    @Override
    public Result delete(Long todoId) {
        this.todoRepository.deleteById(todoId);
        return new SuccessResult("Todo deleted");
    }

    @Override
    public void delete(Todo todo) {
        this.todoRepository.delete(todo);
    }

    @Override
    public DataResult<List<Todo>> getAllTodo() {
        return new SuccessDataResult<List<Todo>>
                (todoRepository.findAll(),"Todos listed.");

    }

    @Override
    public List<TodoDto> getAllUsersTodos(Todo todo) {
        return todoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Result getTodo(Long todoId) {
        this.todoRepository.findById(todoId);
        return new SuccessResult("Todo listed for id");
    }

    @Override
    public Result changeIsActiveByTodos(Long todoId) {
        Todo todoIsActiveUser = this.todoRepository.getById(todoId);
        todoIsActiveUser.setComplete(!todoIsActiveUser.isComplete());
        this.todoRepository.save(todoIsActiveUser);
        return new SuccessResult("User changed the state of the todo process.");
    }

    @Override
    public DataResult<List<Todo>> getOneByUser(Long id) {
        return new SuccessDataResult<List<Todo>>
                (this.todoRepository.getOneByUser(id),"Retrieved by user id");
    }

    @Override
    public List<Todo> getByTodoNameContains(String todoName) {
        return this.todoRepository.getByTodoNameContains(todoName);
    }

    @Override
    public List<Todo> getByTodoNameStartsWith(String todoName) {
        return this.todoRepository.getByTodoNameStartsWith(todoName);
    }

    @Override
    public DataResult<List<Todo>> getOneByUserName(String userName) {
        return new SuccessDataResult<List<Todo>>
                (this.todoRepository.getOneByUserName(userName),"User fetch by username");
    }

    public DataResult<List<Todo>> getAllDescByCreatedDate() {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        return new SuccessDataResult<List<Todo>>
                (this.todoRepository.findAll(sort),"Sort Success");
    }

    // Business Engine

    private Result todoNameNullChecker(TodoDto todo) {
        if(todo.getTodoName().length() < 2) {
            return new ErrorResult("Todo name must be greater than 2 characters");

        }else{
            return new SuccessResult();
        }
    }

    private Result todoDescriptionNullChecker(TodoDto todo) {
        if(todo.getTodoDescription() == null) {
            return new ErrorResult("Description is required");
        }
        return new SuccessResult();
    }

    private TodoDto convertToDto(Todo todo) {
        return TodoDto.builder()
                .todoName(todo.getTodoName())
                .todoDescription(todo.getTodoDescription())
                .userId(todo.getUser().getId())
                .build();
    }

}
