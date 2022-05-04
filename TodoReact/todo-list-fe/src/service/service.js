import axios from "axios";

const list = "http://localhost:8080/todos/getAll"
const del = "http://localhost:8080/todos/delete"
const add = "http://localhost:8080/todos/v2/addDto"

class TodoService{

    list(){
        return axios.get(list)
    }

    delete(note) {
        return axios.delete(del, {
            data: {
                todoId: note.todoId
            }
        })
    }

    add(note) {
        return axios.post(add, note)
    }
}

export default new TodoService();