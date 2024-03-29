import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
import {Card} from "react-bootstrap";
import axios from "axios";
import logo from './logo.png';
import "../style.css"
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
      setTimeout(()=>{ refr()},100)
  }, [])

  return (
      <Container
          className="d-flex justify-content-center align-items-center"
          style={{height: window.innerHeight - 54}}>
        <Card style={{width:400}} className="border border-dark p-5">
            <img src={logo} alt="logo" />
          <p1>
            Username: <strong>{info.username}</strong><br></br>
          </p1>
          <br></br>
          <p3>
            Balance: <strong>{info.account}</strong><br></br>
          </p3>
          <p2>
            Tariff Number{": "} <strong>{info.contracts_id}</strong><br></br>
            Tariff Name{": "} <strong>{info.contracts}</strong><br></br>
            Price{": "} <strong>{info.contracts_sum}</strong> <p2> per minute</p2>     <br></br>
            Minutes{": "} <strong>{info.minutes}</strong><br></br>
            Sum to pay{": "} <strong>{info.fac}</strong><br></br>
          </p2>
          <br></br>
          <p2>
            Roles{": "}<br></br>
            {stat.roles && stat.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}
          </p2>
          <button className="btn btn-primary btnlnk btnm"  >
            <a href="/story" className="lnk">Transaction story</a>
          </button>
        </Card>
      </Container>
  );
};
export default Profile;
