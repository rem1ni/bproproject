import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth.service";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
import {Card} from "react-bootstrap";
export default class Profile extends Component {
  constructor(props) {
    super(props);
    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" }
    };
  }
  ref(iduser){
    const currentUser = AuthService.refresh(iduser);
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({ currentUser: currentUser, userReady: true });
  }
  componentDidMount() {
    const currentUser1 = AuthService.getCurrentUser();
    let iduser= currentUser1.id;
    this.ref(iduser);
    this.timer = setInterval( ()=>this.ref(iduser),5000)
  }
componentWillUnmount() {
    this.timer=null;
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
          <p1 align="center">
            Username: <strong>{currentUser.username}</strong><br></br>
          </p1>
          <br></br>
          <p3>
          Balance: <strong>{currentUser.account}</strong><br></br>
          </p3>
          <p2>
          Contract_id{": "} <strong>{currentUser.contract_id}</strong><br></br>
          Contract{": "} <strong>{currentUser.contract}</strong><br></br>
          Tarrif{": "} <strong>{currentUser.contract_sum}</strong> <p5>per min</p5><br></br>
          Minutes{": "} <strong>{currentUser.minutes}</strong><br></br>
          Sum to pay{": "} <strong>{currentUser.fac}</strong><br></br>
          </p2>
          <br></br>
          <p2>
          Roles{": "}<br></br>
          {currentUser.roles && currentUser.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}
          </p2>
          <div className="ml-auto" >
            <Link to="/story">История операций</Link>
          </div>
      </Card>: null}
      </Container>
    );
  }
}
