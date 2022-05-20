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
            {/* <div className="form-check form-switch">
                <input className="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked={checked} onChange={handleChange}/>
                    <label className="form-check-label" htmlFor="flexSwitchCheckDefault"> Employe</label>
                    <input className="form-check-input" type="checkbox" id="flexSwitchCheckDefault2" checked={checked} onChange={handleChange2}/>
                        <label className="form-check-label" htmlFor="flexSwitchCheckDefault2">Admin</label>
            </div> */}
            Employee
            <input type="checkbox" className=" form-check form-switch form-check-input" checked={checked} onChange={handleChange} />
            Admin
            <input type="checkbox" className=" form-check form-switch form-check-input" checked={checked2} onChange={handleChange2} />
            <br/>
            <button onClick={addNewPost} className="btn btn-primary">Готово</button>



        </form>

    );
};

export default PostForm2;