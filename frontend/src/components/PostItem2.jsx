import React from 'react';

const PostContract = (props) => {
    return (
        <div className="post">
            <div className="post_content">
                <strong>Сумма: {props.post.pay}</strong>
                <strong>    Баланс: {props.post.balance}</strong>
                <div>Время: {props.post.time}</div>
            </div>
        </div>
    );
};

export default PostContract;