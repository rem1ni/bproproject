import React from 'react';
import AuthService from "../services/auth.service";
import axios from "axios";
import GetInfo from "./GetInfo";
const PostContract = (props) => {
    const currentUser = AuthService.getCurrentUser();
    let idcon = currentUser.contract_id;
    let idcontract=props.post.id;
    let iduser=currentUser.id;
function changeTar() {
    GetInfo(iduser);
    window.location.reload();
}
    return (
        <div className="post">
            <div className="post_content">
                <strong>{props.post.id}. {props.post.name}</strong>
                <div>{props.post.sum}</div>
                { (idcon === idcontract) ?
                    (<div>
                    Выбран данный тариф
                </div>)
                :
                    (<button className="btn btn-success"
                onClick= {changeTar}
                >
                    Выбрать
                </button>)}
            </div>
        </div>
    );
};

export default PostContract;