import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
import {Card} from "react-bootstrap";
import axios from "axios";

 const Profile = () => {
  const [info,setInfo]=useState([]);
  const [stat,setStat]=useState([]);
  function refr () {
      const currentUser1 = AuthService.getCurrentUser();
      setStat(currentUser1);
      let iduser= currentUser1.id;
      axios
          .post("http://localhost:8080/info", {
              iduser
          })
          .then(response => {
              setInfo(response.data);
          });
     }

  useEffect(() => {
    return()=>{ refr()}
  }, [])

  return (
      <Container
          className="d-flex justify-content-center align-items-center"
          style={{height: window.innerHeight - 54}}>
        <Card style={{width:400}} className="p-5">
          <p1 align="center">
            Id{ ":"}
            <strong> {stat.id} </strong>
          </p1>
          <p1 align="center">
            Username: <strong>{info.username}</strong><br></br>
          </p1>
          <br></br>
          <p3>
            Balance: <strong>{info.account}</strong><br></br>
          </p3>
          <p2>
            Contract_id{": "} <strong>{info.contracts_id}</strong><br></br>
            Contract{": "} <strong>{info.contracts}</strong><br></br>
            Tarrif{": "} <strong>{info.contracts_sum}</strong> <p5>per min</p5><br></br>
            Minutes{": "} <strong>{info.minutes}</strong><br></br>
            Sum to pay{": "} <strong>{info.fac}</strong><br></br>
          </p2>
          <br></br>
          <p2>
            Roles{": "}<br></br>
            {stat.roles && stat.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}
          </p2>
          <div className="ml-auto" >
            <Link to="/story">История операций</Link>
          </div>
        </Card>
      </Container>
  );
};
export default Profile;
