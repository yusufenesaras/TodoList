import { useEffect, useState } from "react";
import { Switch, Route } from "react-router-dom";
import TodoList from "./components/TodoList";
import TodoAdd from "./components/TodoAdd";
import {
  Nav,
  NavItem,
  NavLink,
  Dropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";

function App() {
  return (
    <div>
      <div>
        <Nav pills style={{ textAlign: "center" }}>
          <NavItem>
            <NavLink active href="#">
              TODO LIST
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="#">Login</NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="#">Register</NavLink>
          </NavItem>
          <NavItem>
            <NavLink disabled href="#">
             About
            </NavLink>
          </NavItem>
        </Nav>
      </div>
      <Switch>
        <Route exact path="/" render={(props) => <TodoList />} />
        <Route exact path="/add" render={(props) => <TodoAdd />} />
        {/* //  <Route exact path="/update/:id" render={props => (<TodoUpdate {...props} />)} /> */}
      </Switch>
    </div>
  );
}

export default App;
