import React from 'react';

const PostContract = (props) => {
    return (
        <div className="post">
            <div className="post_content">
                <strong>{props.post.id}. {props.post.pay}</strong>
                <div>{props.post.time}</div>
            </div>
        </div>
    );
};

export default PostContract;