import React, { useState, useMemo, useEffect} from 'react';
import AuthService from "../services/auth.service";
import axios from "axios";
import {UseFetching} from "../UseFetching";
import PostService from "../PostService";
import PostItem from "./PostItem";
import PostItem2 from "./PostItem2";
const Story = () => {

   function send() {
       const currentUser = AuthService.getCurrentUser();
       let iduser = currentUser.id;
        return axios
            .post("http://localhost:8080/bpro/userpay/info", {
                iduser
            })
            .then(response => {
                setPosts(response.data);
            });
    }

    useEffect(() => {
        send()
    }, [])

    const[posts,setPosts]=useState([])

    return (
        <div >
            <h1 style={{textAlign:'center'}}>История</h1>
            <div className="Blocks">
                {posts.map((post,index) =>
                    <PostItem2  id={index + 1} post={post} key={post.id}/>
                )}
            </div>
        </div>
    );
};

export default Story;