import React, {useEffect, useState} from "react";
import AuthService from "../services/auth.service";
import axios from "axios";
import Edit from "./MyModal";
import MyModal from "./MyModal";
import PostForm from "./PostForm";
import "../style.css"
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
                <div><strong>Price: {props.post.sum}</strong></div>

                { (idcon === idcontract) ?
                    (<div>
                        This tariff is selected
                </div>)
                :
                    (<button className="btn btn-primary btnmar"
                onClick= {changeTar}
                >
                    Choose
                </button>)}


                 { 
                     ((currentUser1.roles.includes("ROLE_EMPLOYEE") === true)&&(props.post.id != 1))?
                     (
                     <button className="btn btn-btn-primary btnmar"
                     onClick={() => setModal(true)}
                     >
                     Edit
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