import React, { Component } from 'react'
import UserService from '../services/user.service';
import axios from "axios";
import PostForm2 from "./PostForm2";
import MyModal2 from "./MyModal2";
import "../style.css";
export default class BoardAdmin extends React.Component {
    del(iduser){
        return axios
            .post("http://localhost:8080/bpro/delete", {
                iduser
            })
            .then(response => {
                window.location.reload();
            });
    }
    add(iduser,min){
        return axios
            .post("http://localhost:8080/bpro/minutes", {
                iduser,
                min
            })
            .then(response => {
                window.location.reload();
            });
    }

    setModal(props){
        this.setState({ modal: props});
    }

    check(checked,checked2){
        let iduser = JSON.parse(localStorage.getItem('idu'));
        let check1=Number(checked);
        let check2=Number(checked2);
        axios
            .post("http://localhost:8080/bpro/role", {
                iduser,
                check1,
                check2
            });
        window.location.reload();
    }
    constructor(props) {
        super(props)

        this.state = {
            users: [],
            min: 0,
            modal:false,
            id:0
        }
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
        this.setState({min: event.target.value});
    }
    componentDidMount() {
        UserService.getAdminBoard().then((response) => {
            this.setState({ users: response.data , min:0});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">User List</h2>

                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Id </th>
                                <th>User Name</th>
                                <th>Balance</th>
                                <th>Minutes</th>
                                <th>Tariff</th>
                                <th>Add minutes</th>
                                <th>Roles</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.map(
                                    user => 
                                    <tr key={user.id}>
                                        <td>{user.id}</td>
                                        <td>{user.username}</td>
                                        <td>{user.account}</td>
                                        <td>{user.minutes}</td>
                                        <td><p2>
          Tariff name{": "} <strong>{user.contract.name}</strong><br></br>
          Sum to pay{": "} <strong>{user.contract.sum}</strong><br></br>
          </p2></td>
                                        <td>
                                            <form>
                                                <input type="number"  className="form-control" placeholder="Minutes"  onChange={this.handleChange} />
                                                <button className="btn btn-primary btnm" onClick={()=> this.add(user.id, this.state.min )}>Send</button>
                                            </form>

                                        </td>
                                        <td>
                                            <button className="btn btn-primary btnm"
                                                    onClick={() => {
                                                        localStorage.setItem("idu",JSON.stringify(user.id));
                                                        this.setModal(true)
                                                    }}
                                            >Change role</button>
                                        </td>
                                        <td>
                                            <button className="btn btn-primary btnm" onClick={()=> this.del(user.id)}>Delete</button>
                                        </td>
                                    </tr>
                                )

                            }
                        </tbody>
                    </table>
                <MyModal2 visible={this.state.modal} setVisible={()=> this.setModal()} >
                    <PostForm2 create={this.check}   />
                </MyModal2>
            </div>
        )
    }
}
