import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import axios from "axios";
import Edit from "./MyModal";
import MyModal from "./MyModal";
import PostForm from "./PostForm";

const PostContract = (props) => {
    const [modal, setModal] = useState(false);
    const currentUser = AuthService.getCurrentUser();
    let idcon = currentUser.contract_id;
    let idcontract=props.post.id;
    let iduser=currentUser.id;
    const[emp,setEmp]=useState(false);
    setEmp(currentUser.roles.includes("ROLE_EMPLOYEE"));

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
            .post("http://localhost:8080/edit", {
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

                { (emp===true) ? (<button className="btn btn-success"
                                          onClick={() => setModal(true)}
                >
                    Выбрать
                </button>
                ): <div/>

                }
            </div>
            <MyModal visible={modal} setVisible={setModal}>
                <PostForm create={addEdit}/>
            </MyModal>
        </div>
    );
};

export default PostContract;