import React,{useState} from 'react';
import axios from "axios";
import { Switch, Route, Link } from "react-router-dom";
import AuthService from "../services/auth.service";
import $ from 'jquery';
const Balance = () => {
    $('body').on('input', '.input-range', function(){
        let value = this.value.replace(/[^0-9]/g, '');
        if (value < $(this).data('min')) {
            this.value = $(this).data('min');
        } else if (value > $(this).data('max')) {
            this.value = $(this).data('max');
        } else {
            this.value = value;
        }
    });
    const [sum,setSum]=useState('');
    const[mes,setMes] = useState("");
    function Send() {
        let num = Number(sum);
        const currentUser = AuthService.getCurrentUser();
        let i = currentUser.id;


        return axios
            .post("http://localhost:8080/bpro/userpay", {
                i,
                num
            }).then(response => {
                setMes(response.data);

            });
    }
    return (
        <div className="container mt-5">
            <div className="col-md-4 col-md-offset-4  p-3 m-lg-auto">
                Введите сумму пополнения:
                <input className="input-range form-control mt-2" type="text" required data-min="0" data-max="10000" placeholder="Сумма"
                       value={sum}
                       onChange={e => setSum(e.target.value)}
                />
            </div>
            <div className="row ">
                <div className="col-md-4 col-md-offset-4 border border-dark p-3 m-lg-auto">
                    <div className="credit-card-div">
                        <div className="panel panel-default">
                            <div className="panel-heading">

                                <div className="row ">
                                    <div className="col-md-12">
                                        <input className="input-range form-control" type="text" required data-min="0" data-max="9999999999999999" placeholder="Номер карты" />
                                    </div>
                                </div>

                                <div className="row ">
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <span className="help-block text-muted small-font"> Month</span>
                                        <input className="input-range form-control" type="text" required data-min="0" data-max="12" placeholder="MM"/>
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <span className="help-block text-muted small-font"> Year</span>
                                        <input className="input-range form-control" type="text" required data-min="0" data-max="25" placeholder="YY"/>
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <img src="https://bootstraptema.ru/snippets/form/2016/form-card/card.png"
                                             className="img-rounded"/>
                                    </div>
                                    <div className="col-md-3 col-sm-3 col-xs-3">
                                        <span className="help-block text-muted small-font"> CVV</span>
                                        <input type="password" className="input-range form-control" required data-min="0" data-max="999" placeholder="CVV"/>
                                    </div>
                                </div>

                                <div className="row mt-3">
                                    <div className="col-md-12 pad-adjust">

                                        <input type="text" required className="form-control" placeholder="Имя"/>
                                    </div>
                                </div>

                                <div className="row mt-3">
                                    <div className="col-md-6 col-sm-6 col-xs-6 pad-adjust">
                                        <button onClick={() => {
                                            Send(sum);
                                        }
                                        }>Оплатить</button>
                                    </div>
                                </div>

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