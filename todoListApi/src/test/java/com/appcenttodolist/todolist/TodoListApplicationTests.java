package com.appcenttodolist.todolist;

import com.appcenttodolist.todolist.core.ModelMapperDto.DtoService;
import com.appcenttodolist.todolist.core.Results.DataResult;
import com.appcenttodolist.todolist.dto.TodoDto;
import com.appcenttodolist.todolist.entity.Todo;
import com.appcenttodolist.todolist.entity.User;
import com.appcenttodolist.todolist.repository.TodoRepository;
import com.appcenttodolist.todolist.service.concretes.TodoManager;
import org.junit.jupiter.api.*;
import java.time.Clock;
import java.util.List;


import static org.mockito.Mockito.*;


class TodoListApplicationTests {

    private TodoManager todoService;
    private TodoRepository todoRepository;
    private DtoService dtoService;

    @BeforeEach
    public void setup() {
        todoRepository = mock(TodoRepository.class);
        dtoService = mock(DtoService.class);
        Clock clock = mock(Clock.class);

        todoService = new TodoManager(todoRepository,dtoService);

        when(clock.getZone()).thenReturn(Clock.systemDefaultZone().getZone());
    }

    @Test
    @Order(3)
    public void whenTodoIsAddedandListed(){


        User user =  User.builder()
                .id(1L)
                .userName("YusufEnes")
                .email("enesaras551@gmail.com")
                .password("!@qaz@!")
                .build();


        Todo todo = new Todo();
        todo.setTodoId(1L);
        todo.setTodoName("Java");
        todo.setTodoDescription("Java collection framework");


        Todo add = todoRepository.save(todo);

        List<Todo> todos = todoRepository.findAll();

    }

}

