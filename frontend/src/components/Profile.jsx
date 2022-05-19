import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import Container from "react-bootstrap/Container";
import {Link} from "react-router-dom";
import {Card} from "react-bootstrap";
import axios from "axios";
import logo from './unknown.png';
import "../App.css";
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
          className="profile_container">
        <Card  className="card">
            <div className="profile_username">
                <strong>{info.username}</strong>
            </div>
            <div className="profile_data">
                <div>Balance:<strong>{info.account}</strong></div>
                <div>Contract: <strong>{info.contracts_id}</strong></div>
                <div>Tarif: <strong>{info.contracts}</strong></div>
                <div>Minutes: <strong>{info.minutes}</strong></div>
                <div>Sum to pay: <strong>{info.fac}</strong></div>
                <div>Transactions Story: <Link to="/story" className="card_btn">Check</Link></div>
                <div>
                    Roles: {stat.roles && stat.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}

                </div>
            </div>

            {/*Номер тарифа{": "} <strong>{info.contracts_id}</strong><br></br>*/}
            {/*Тариф{": "} <strong>{info.contracts}</strong><br></br>*/}
            {/*Стоимость{": "} <strong>{info.contracts_sum}</strong> <p5>за минуту</p5><br></br>*/}
            {/*Минуты{": "} <strong>{info.minutes}</strong><br></br>*/}
            {/*Сумма к оплате{": "} <strong>{info.fac}</strong><br></br>*/}

          {/*<p2>*/}
          {/*  */}
          {/*  {stat.roles && stat.roles.map((role, index) => <strong key={index}>{role}<br></br></strong>)}*/}
          {/*</p2>*/}
          {/*<div className="ml-auto" >*/}
          {/*  <Link to="/story">История операций</Link>*/}
          {/*</div>*/}
        </Card>
      </Container>
  );
};
export default Profile;
