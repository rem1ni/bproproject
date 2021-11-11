import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth.service";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
export default class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <Container
          className="d-flex justify-content-center align-items-center"
          style={{height: window.innerHeight - 54}}>
        {(this.state.userReady) ?
        <Card style={{width:400}} className="p-5">
        <p1 align="center">
          Id{ ":"}
         <strong> {currentUser.id} </strong>
        </p1>
          <p1>
            Username: <strong>{currentUser.username}</strong><br></br>
          </p1>
          <br></br>
          <p3>
          Balance: <strong>{currentUser.account}</strong><br></br>
          </p3>
          <p2>
          Contract{": "} <strong>{currentUser.contract}</strong><br></br>
          Sum to pay{": "} <strong>{currentUser.contract_sum}</strong><br></br>
          </p2>
          <br></br>
          <p2>
          Roles{": "}<br></br>
          {currentUser.roles && currentUser.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}
          </p2>
          <div className="ml-auto" >
            <Link to="/profile">История операций</Link>
          </div>
      </Card>: null}
      </Container>
    );
  }
}
