import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import axios from "axios";
import Edit from "./MyModal";
import MyModal from "./MyModal";
import PostForm from "./PostForm";

const PostContract = (props) => {
    const [modal, setModal] = useState(false);
    const currentUser1 = AuthService.getCurrentUser();
    let currentUser=[];
    let iduser=currentUser1.id;
     axios
        .post("http://localhost:8080/info", {
            iduser
        })
        .then(response => {
             currentUser=(response.data);
        });
    let idcon = currentUser.contract_id;
    let idcontract=props.post.id;
    




function changeTar() {
    return axios
        .post("http://localhost:8080/bpro/usercontract", {
            iduser,
            idcontract
        }).then(response => {

            window.location.assign('http://localhost:3000/profile')
        });

}
    const addEdit = (newPost) => {
        let title = newPost.title;
        let body = newPost.body;
        axios
            .post("http://localhost:8080/bpro/edit", {
                idcontract,
                title,
                body
            });
        setModal(false)
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


                {
                    (currentUser.roles.includes("ROLE_EMPLOYEE") === true)?
                        (
                            <button className="btn btn-success"
                                    onClick={() => setModal(true)}
                            >
                                Редактировать
                            </button>
                        ): (<div/>)
                }

                
            </div>
            <MyModal visible={modal} setVisible={setModal}>
                <PostForm create={addEdit}/>
            </MyModal>
        </div>
    );
};

export default PostContract;