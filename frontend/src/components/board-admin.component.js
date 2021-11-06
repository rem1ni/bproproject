import React, { Component } from 'react'
import UserService from '../services/user.service';

export default class BoardAdmin extends React.Component {

    constructor(props) {
        super(props)

        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getAdminBoard().then((response) => {
            this.setState({ users: response.data});
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
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
            </div>
        )
    }
}
