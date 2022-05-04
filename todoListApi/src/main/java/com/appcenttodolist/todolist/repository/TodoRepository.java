package com.appcenttodolist.todolist.repository;

import com.appcenttodolist.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> getByTodoNameContains(String todoName);
    List<Todo> getByTodoNameStartsWith(String todoName);

    @Query("From Todo where isComplete = false and todoId =:id")
    List<Todo> getUsersActiveTodos(Long id);

    @Query("From Todo where user.id=:id")
    List<Todo> getOneByUser(Long id);

    @Query("From Todo where user.userName=:userName")
    List<Todo> getOneByUserName(String userName);


}
