import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import Start from "./components/Start.jsx";
import AuthService from "./services/auth.service";
import Login from "./components/login.component";
import Register from "./components/register.component";
import Profile from "./components/Profile";
import BoardAdmin from "./components/board-admin.component";
import EventBus from "./common/EventBus";
import Balance from "./components/Balance";
import Story from "./components/Story";
import Edit from "./components/MyModal";

class App extends Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      showModeratorBoard: false,
      showAdminBoard: false,
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = AuthService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        showModeratorBoard: user.roles.includes("ROLE_EMPLOYEE"),
        showAdminBoard: user.roles.includes("ROLE_ADMIN"),
      });
    }
    
    EventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    EventBus.remove("logout");
  }

  logOut() {
    AuthService.logout();
    this.setState({
      showAdminBoard: false,
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser, showModeratorBoard, showAdminBoard } = this.state;

    return (

          <div>
            <nav className='nav'>
              {currentUser ? (
                  <div className="nav_links">
                    <li>
                      <a href={"/profile"} className="Links">
                        {currentUser.username}
                      </a>
                    </li>
                    
                    {showAdminBoard && (
                    <li>
                      <a href={"/admin"} className="Links">
                        Админ
                      </a>
                    </li>
                )}

                    <li >
                      <a href={"/contract"} className="Links">
                        Тарифы
                      </a>
                    </li>
                    <img src="./Logo.png" alt="" className="nav_logo"/>
                    <li>
                      <a href={"/balance"} className="Links">
                        Баланс
                      </a>
                    </li>
                    <li >
                      <a href="/login"  onClick={this.logOut}>
                        Выйти
                      </a>
                    </li>
                  </div>
              ) : (
                  <div className="nav_links">
                    <li>
                      <a href={"/login"} className="Links">
                        Login
                      </a>
                    </li>

                    <li >
                      <a href={"/register"} className="Links">
                        Sign Up
                      </a>
                    </li>

                  </div>
              )}
            </nav>

            <div className="container mt-3">
              <Switch>
                <Route exact path="/login" component={Login} />
                <Route exact path="/register" component={Register} />
                <Route exact path="/profile">
                  <Profile/>
                </Route>
                <Route path="/admin" component={BoardAdmin} />
                <Route path="/contract">
                  <Start/>
                </Route>
                <Route path="/balance">
                  <Balance/>
                </Route>
                <Route path="/story">
                  <Story/>
                </Route>
                <Route path="/edit">
                  <Edit/>
                </Route>
              </Switch>
            </div>

            { /*<AuthVerify logOut={this.logOut}/> */ }
          </div>

    );
  }
}
export default App;
