import React from 'react';
import cl from './MyModal.module.css';

const MyModal = ({children, visible, setVisible}) => {
    const rootClasses = [cl.myModal]

    if (visible) {
        rootClasses.push(cl.active);
    }

    return (
        <div className={rootClasses.join(' ')}  >
            <div className={cl.myModalContent} onClick={(e) => e.stopPropagation()}>
                {children}
                <button className="alert " onClick={() => {
                    setVisible(false)
                }}>Отмена</button>
            </div>


        </div>
    );
};

export default MyModal;