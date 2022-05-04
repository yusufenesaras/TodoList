import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import { Button, Table } from "reactstrap";
import service from "../service/service";
import alertify from "alertifyjs";
import "./TodoList.css";

function TodoList() {
  const [todo, setTodo] = useState([]);

  let history = useHistory();

  useEffect(() => {
    service.list().then((result) => {
      setTodo(result.data.data);
    });
  });

  const deleteNote = (todoId) => {
    let del = { todoId };
    service.delete(del).then(alertify.success("Todo deleted")).catch();
  };

  const addNote = () => {
    history.push("/add");
  };

  return (
      
    <main id="todolist">
      <h1>
        Todo List
        <span>Get things done, one item at a time.</span>
      </h1>

      <template v-if="todo.length">
        <transition-group name="todolist" tag="ul">
          <li v-for="item in todoByStatus">
            <span class="label"></span>
            <div class="actions">
              <button class="btn-picto" type="button">
                <i aria-hidden="true" class="material-icons"></i>
              </button>
              <button
                class="btn-picto"
                type="button"
                aria-label="Delete"
                title="Delete"
              >
                <i aria-hidden="true" class="material-icons">
                  delete
                </i>
              </button>
            </div>
          </li>
        </transition-group>
        <togglebutton label="Move done items at the end?" name="todosort" />
      </template>
      {todo.map((t, index) => (
        <p v-else class="emptylist">
          {t.todoName} <span>-</span> {t.todoDescription} <span>-</span>
          <Button style={{borderRadius:"20px"}} onClick={() => deleteNote(t.todoId)} type="submit" color="danger">
            Delete
          </Button>
        </p>
      ))}

      <form name="newform">
          <button onClick={() => addNote()} type="submit">
            Add
          </button>
          <button type="submit">
            Update
          </button>
      </form>
    </main>
  );
}

export default TodoList;
