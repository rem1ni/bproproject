import React from 'react';
import axios from 'axois';
const GetInfo = (iduser) => {

    return axios
        .post("http://localhost:8080/bpro/allinfo/", {
            iduser
        })
        .then(response => {

                localStorage.setItem("user", JSON.stringify(response.data));

        });
};

export default GetInfo;