import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import axios from "axios";
import Edit from "./MyModal";
import MyModal from "./MyModal";
import PostForm from "./PostForm";

const PostContract = (props) => {
    const [modal, setModal] = useState(false);

    const currentUser = JSON.parse(localStorage.getItem("myKey"));
    let idcon = currentUser.contracts_id;
    let idcontract=props.post.id;
    const currentUser1 = AuthService.getCurrentUser();
    let iduser=currentUser1.id;

function changeTar() {
    return axios
        .post("http://localhost:8080/bpro/usercontract", {
            iduser,
            idcontract
        }).then(response => {
            let i = AuthService.ref(iduser);
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
                <div><strong>Стоимость за минуту: {props.post.sum}</strong></div>

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
                    (currentUser1.roles.includes("ROLE_EMPLOYEE") === true)?
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