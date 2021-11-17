import React, {useState} from 'react';
import axios from "axios";

export const UseFetching = (callback) => {
    const [isLoading,setIsLoading]=useState(false);
    const [error,setError]=useState('');
    const fetching = async (...args) =>{
        try{
            setIsLoading(true)
            await callback(...args)
        }catch (e) {
            setError(e.message)
        }finally {
            setIsLoading(false)
        }
    }
    return [fetching,isLoading,error]
};