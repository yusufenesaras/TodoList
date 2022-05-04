package com.appcenttodolist.todolist.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "todo")
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 500, name = "todo_id")
    private Long todoId;

    //@NotNull
    @Column(name = "todo_name", nullable = false)
    private String todoName;

    @Column(name = "todo_description")
    private String todoDescription;

    @Column(name = "created_date")
    private Date createdDate = new Date(System.currentTimeMillis()); //eklendiği an tarih atılır

    @Column(name = "is_complete")
    private boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY) //bir user tarafından birçok todo oluşturulabilir
    @JoinColumn(name = "id", nullable = false)
    private User user;


    // @field:Min(0, message = "Initial Credit value must not be negative value")
}

