import React from 'react';
import cl from './MyModal.module.css';
import "../style.css"
const MyModal = ({children, visible, setVisible}) => {
    const rootClasses = [cl.myModal]

    if (visible) {
        rootClasses.push(cl.active);
    }

    return (
        <div className={rootClasses.join(' ')}  >
            <div className={cl.myModalContent} onClick={(e) => e.stopPropagation()}>
                {children}
                <button className="btn btn-danger btn_success" onClick={() => {
                    setVisible(false)
                }}>Cancel</button>
            </div>


        </div>
    );
};

export default MyModal;