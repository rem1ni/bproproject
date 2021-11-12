import React from 'react';

const PostContract = (props) => {
    return (
        <div className="post">
            <div className="post_content">
                <strong>{props.post.id}. {props.post.name}</strong>
                <div>{props.post.sum}</div>
            </div>
        </div>
    );
};

export default PostContract;