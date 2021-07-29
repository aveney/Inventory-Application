
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { Component } from 'react';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import UserList from './UserList';
import UserEdit from './UserEdit';
import UserAdd from './UserAdd';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/user' exact={true} component={UserList}/>
            <Route path='/user/newUser' exact={true} component={UserAdd}/>
            <Route path='/user/:id' component={UserEdit}/>
          </Switch>
        </Router>
    )
  }
}
export default App;