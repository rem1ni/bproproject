import React, {useState} from 'react';



const PostForm2 = ({create}) => {
    const [checked, setChecked] = useState(false);
    const [checked2, setChecked2] = useState(false);

    function handleChange() {
        setChecked(!checked); // инвертируем стейт
    }
    function handleChange2() {
        setChecked2(!checked2); // инвертируем стейт
    }
    const addNewPost = (e) => {
        e.preventDefault()
        create(checked,checked2)
    }
    return (
        <form>
            Работник
            <input type="checkbox" checked={checked} onChange={handleChange} />
            <br/>
            Админ
            <input type="checkbox" checked={checked2} onChange={handleChange2} />
            <br/>
            <button onClick={addNewPost}>Готово</button>
        </form>

    );
};

export default PostForm2;