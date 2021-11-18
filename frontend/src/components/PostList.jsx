import React from 'react';
import PostItem from "./PostItem";

const PostList = ({posts,title}) => {
    if(!posts.length){
        return(
            <h1 style={{textAlign:"center"}}>
            Тут пока пусто
            </h1>
        )
    }
    delete posts[0];
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