import React, {useRef, useState, useMemo, useEffect} from 'react';
import PostList from "../components/PostList";
import axios from 'axios';
import PostService from "../PostService";
import {UseFetching} from "../UseFetching";

function Start() {

    const [fetchPosts, isPostsLoading, postError] = UseFetching(async () => {
        const response = await PostService.getAll();
        setPosts(response.data)
    })

    useEffect(() => {
        fetchPosts()
    }, [])

    const[posts,setPosts]=useState([])

    return (
        <div style={{margin: "25px"}} className="App">
            <PostList posts={posts} title={'Tariff'}/>
        </div>
    );
}

export default Start;