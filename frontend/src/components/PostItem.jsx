import React from 'react';
import AuthService from "../services/auth.service";
const PostContract = (props) => {

    const currentUser = AuthService.getCurrentUser();
    let idcontract = currentUser.contract.id;
    return (
        <div className="post">
            <div className="post_content">
                <strong>{props.post.id}. {props.post.name}</strong>
                <div>{props.post.sum}</div>
                { (idcontract === props.post.id) ?
                    (<div>
                    Выбран данный тариф
                </div>)
                :
                    (<button

                >
                    Выбрать
                </button>)}
            </div>
        </div>
    );
};

export default PostContract;