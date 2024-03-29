import React,{useState} from 'react';
import axios from "axios";
import { Switch, Route, Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import "../style.css"

const Balance = () => {

    const[num,setNum]=useState('');
    const [m,setM]=useState('');
    const [y,setY]=useState('');
    const [c,setC]=useState('');
    const[mes,setMes] = useState("");
    const handleChange = (e,set) => {
        let a = (e).target.value.replace('.','')
        a = a.replace('-','')
        a = a.replace('+','')
        a = a.replace('e','')
        a = a.replace('e','')
            set(a)
    }

    const [sum,setSum]=useState('');
    const currentUser = AuthService.getCurrentUser();
    let iduser = currentUser.id;
    AuthService.ref(iduser);
    const cu = JSON.parse(localStorage.getItem('myKey'));
    let id=cu.account;
    const Send =(e)=> {
        e.preventDefault()
        if((num.toString().length) < 16){
            setMes('Card number must contain 16 characters')
        } else if(m.toString().length < 2){
            setMes('Month must contain 2 characters')
        }else if(y.toString().length < 2) {
            setMes('Year must contain 2 characters')
        }else if(c.toString().length < 3) {
            setMes('CVV must contain 3 characters')
        }else {
            let num = Number(sum);
            const currentUser = AuthService.getCurrentUser();
            let i = currentUser.id;

            axios
                .post("http://localhost:8080/bpro/userpay", {
                    i,
                    num
                }).then(response => {
                setMes(response.data);

            });
        }
    }
    return (
        <div className="container mt-5">
            <div className="col-md-4 col-md-offset-4  p-3 m-lg-auto">
            Balance: {id}
            <br></br>
            <br></br>
                Sum to pay:
                <input className="input-range form-control mt-2" type="text"  required  placeholder="Sum"
                       value={sum}
                       onChange={(e) => {
                            if(e.target.value !== '.'){
                            if(e.target.value >= 0){
                           if(e.target.value < 10000){
                               handleChange(e,setSum)
                           }else {
                               e.target.value=  '10000'
                               handleChange(e,setSum)
                           }
                       }}}}
                />
            </div>
            <div className="row ">
                <div className="col-md-4 col-md-offset-4 border border-dark p-3 m-lg-auto">
                    <div className="credit-card-div">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                            <form>
                                <div className="row ">
                                    <div className="col-md-12">
                                        <input
                                            className="input-range form-control"
                                            value={num}
                                            onChange={(e) => {
                                                if((Number(e.target.value)>=0)){
                                                if(e.target.value.length <17){
                                                    handleChange(e,setNum)
                                                }else {
                                                    e.target.value=  e.target.value.slice(0,2)
                                                }
                                            }}}
                                            type="text"
                                            placeholder={"0000 0000 0000 0000"}
                                            required
                                        />
                                    </div>
                                </div>

                                <div className="row ">
                                    <div className="col-md-3 col-sm-3 col-xs-3 ">
                                        <span className="help-block text-muted small-font">Month</span>
                                        <input
                                            className="input-range form-control"
                                            value={m}
                                            onChange={(e) => {
                                                if((Number(e.target.value)>=0)){
                                                if(e.target.value.length <3){
                                                    handleChange(e,setM)
                                                    if(e.target.value>12){
                                                        e.target.value='12'
                                                        handleChange(e,setM)}
                                                }
                                                else
                                                {
                                                    e.target.value=  e.target.value.slice(0,2)
                                                }
                                            }}}
                                            type="text"
                                            placeholder={"MM"}
                                            required
                                        />
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <span className="help-block text-muted small-font"> Year</span>
                                        <input
                                            className="input-range form-control"
                                            value={y}
                                            onChange={(e) => {

                                                if(e.target.value.length <3){
                                                    handleChange(e,setY)
                                                    if(e.target.value>26){
                                                        e.target.value='26'
                                                        handleChange(e,setY)}
                                                if(e.target.value<22){
                                                    e.target.value='22'
                                                    handleChange(e,setY)
                                                }
                                                }
                                                else
                                                {
                                                    e.target.value=  e.target.value.slice(0,2)
                                                }
                                            }}
                                            type="number"
                                            placeholder={"YY"}
                                            required
                                        />
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <img src="https://bootstraptema.ru/snippets/form/2016/form-card/card.png"
                                             className="img-rounded"/>
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <span className="help-block text-muted small-font"> CVV</span>
                                        <input
                                            className="input-range form-control"
                                            value={c}
                                            onChange={(e) => {
                                                if((Number(e.target.value)>=0)){
                                                if(e.target.value.length <4){
                                                    handleChange(e,setC)
                                                }
                                                else
                                                {
                                                    e.target.value=  e.target.value.slice(0,2)
                                                }
                                            }}}
                                            type="password"
                                            placeholder={"CVV"}
                                            required
                                        />
                                    </div>
                                </div>

                                <div className="row mt-3">
                                    <div className="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                                        <button className="btn btn-primary btnm" onClick={
                                            Send
                                        }
                                        >Pay</button>
                                    </div>
                                </div>
                            </form>
                            </div>
                        </div>
                    </div>
                    {mes}
                </div>
            </div>
        </div>
    );
};

export default Balance;