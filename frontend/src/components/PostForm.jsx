import React, {useState} from 'react';


const PostForm = ({create}) => {
    const [post, setPost] = useState({title: '', body: ''})


    const addNewPost = (e) => {
        e.preventDefault()
        const newPost = {
            ...post, id: Date.now()
        }
        create(newPost)
        setPost({title: '', body: ''})
    }

    return (
        <form>
            {/*Управляемый компонент*/}
            <input
                value={post.title}
                onChange={e => setPost({...post, title: e.target.value})}
                type="text"
                placeholder="Название тарифа"
            />
            {/*Неуправляемый\Неконтролируемый компонент*/}
            <input
                value={post.body}
                onChange={e => setPost({...post, body: e.target.value})}
                type="text"
                placeholder="Стоимость"
            />
            <button onClick={addNewPost}>Применить</button>
        </form>
    );
};

export default PostForm;