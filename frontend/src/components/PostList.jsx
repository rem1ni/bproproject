import React from 'react';
import PostItem from "./PostItem";
import AuthService from "../services/auth.service";
import axios from 'axios';
const PostList = ({posts,title}) => {
    if(!posts.length){
        return(
            <h1 style={{textAlign:"center"}}>
            Тут пока пусто
            </h1>
        )
    }
    function ref(iduser){
        axios.post("http://localhost:8080/info", {
            iduser
        })
            .then(response => {
                return (response.data);
            })}
    const currentUser1 = AuthService.getCurrentUser();
    let iduser=currentUser1.id;
    const currentUser =  ref(iduser);
    let aboba = currentUser.contract_id;
    localStorage.setItem("myKey",JSON.stringify(aboba));
    return (
        <div >
            <h1 style={{textAlign:'center'}}>{title}</h1>
            <div className="Blocks">
                {posts.map((post,index) =>
                    <PostItem  id={index + 1} post={post} key={post.id}/>
                )}
            </div>
        </div>
    );
};

export default PostList;