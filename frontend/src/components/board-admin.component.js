import React, { Component } from 'react'
import UserService from '../services/user.service';
import axios from "axios";
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
                iduser
                min
            })
            .then(response => {
                window.location.reload();
            });
    }

    constructor(props) {
        super(props)

        this.state = {
            users: [],
            min: 0
        }
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
        this.setState({value: event.target.value});
    }
    componentDidMount() {
        UserService.getAdminBoard().then((response) => {
            this.setState({ users: response.data , min:0});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Users List</h2>

                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Id </th>
                                <th>username</th>
                                <th>Balance</th>
                                <th>Minutes</th>
                                <th>Contract</th>
                                <th>Add minutes</th>
                                <th>Delete </th>
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
          Contract name{": "} <strong>{user.contract.name}</strong><br></br>
          Sum to pay{": "} <strong>{user.contract.sum}</strong><br></br>
          </p2></td>
                                        <td>
                                            <form>
                                                <input type="number"  className="form-control" placeholder="Минуты" value={this.state.value} onChange={this.handleChange} />
                                                <button onClick={()=> this.add(user.id, min )}>Отправить</button>
                                            </form>

                                        </td>
                                        <td>
                                            <button onClick={()=> this.del(user.id)}>Delete</button>
                                        </td>
                                    </tr>
                                )

                            }
                        </tbody>
                    </table>
            </div>
        )
    }
}
