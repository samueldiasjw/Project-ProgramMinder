import React, { Component } from "react";
import Navbar from "./all_components/Navbar/Navbar";
import Footer from "./all_components/Footer/Footer";
import Homepage from "./all_components/Homepage/Homepage";
import Login from "./homepage/Components/Login/Login";
import Register from "./homepage/Components/Register/Register";
import Category from "./homepage/Components/Category/Category";
import Threads from "./homepage/Components/Threads/Threads";
import Posts from "./homepage/Components/Posts/Posts";
import Account from "./homepage/Components/Account/Account";
import CreateThread from "./homepage/Components/CreateThread/CreateThread";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

export default class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Navbar />
          <Switch>
            <Route
              exact
              path="/"
              render={(props) => (
                <div>
                  <Homepage />
                </div>
              )}
            />
            <Route
              path="/register"
              render={(props) => (
                <div>
                  <Register />
                </div>
              )}
            />

            <Route
              exact
              path="/login"
              render={(props) => (
                <div>
                  <Login />
                </div>
              )}
            /> 

            <Route
              path="/login/categorys"
              render={(props) => (
                <div>
                  <Category />
                </div>
              )}
            /> 

            <Route
              path="/category/:id"
              render={(props) => (
                <div>
                  <Threads />
                </div>
              )}
            /> 

            <Route
              path="/post/:id"
              render={(props) => (
                <div>
                  <Posts />
                </div>
              )}
            /> 

            <Route
              path="/account/:id"
              render={(props) => (
                <div>
                  <Account />
                </div>
              )}
            /> 

            <Route
              path="/account/myaccount"
              render={(props) => (
                <div>
                  <Account />
                </div>
              )}
            />  

            <Route
              path="/create/thread/:id"
              render={(props) => (
                <div>
                  <CreateThread />
                </div>
              )}
            /> 

          </Switch>
          <Footer />
        </div>
      </Router>
    );
  }
}
