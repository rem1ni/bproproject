import React from 'react';

const PostContract = (props) => {
    return (
        <div className="post">
            <div className="post_content">
                <strong>Sum: {props.post.pay}</strong>
                <div>Time: {props.post.time}</div>
            </div>
        </div>
    );
};

export default PostContract;