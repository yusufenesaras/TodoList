import { useState } from "react";
import service from "../service/service";
import alertify from "alertifyjs";
import { useHistory } from "react-router-dom";

export default function TodoAdd() {
  const [todoName, setTodoName] = useState("");
  const [todoDescription, setTodoDescription] = useState("");
  let history = useHistory();

  const add = () => {
    let note = {
      todoName: todoName,
      todoDescription: todoDescription,
      userId: 1,
    };
    service
      .add(note)
      .then(() => alertify.success("Todo added", 1.5), history.push("/"))
      .catch(
        () => alertify.danger("Todo not be added", 1.5),
        history.push("/")
      );
  };

  return (
    <div style={{backgroundColor:"#FF6666"}} className="container card col-md-6 offset-md-3 offset-md-3">
      <div className="card-body">
        <form>
          <div className="text-center">Not Ekle</div>
          <br />
          <input
            onChange={(e) => setTodoName(e.target.value)}
            placeholder="Todo name"
            className="form-control"
          />
          <br /> <br/> <br/>
          <textarea
            onChange={(e) => setTodoDescription(e.target.value)}
            placeholder="Todo description"
            className="form-control"
          />
          <br />
          <br/>
          <button style={{color:"white"}} className="btn btn-success" onClick={() => add()}>
            Add
          </button>
        </form>
      </div>
    </div>
  );
}
