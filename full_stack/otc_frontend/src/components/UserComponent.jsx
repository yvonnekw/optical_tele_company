import React from 'react';
import { useNavigate } from 'react-router-dom';




const UserComponent = () => {

    const navigator = useNavigate();

    function makeNewCall(){
        navigator('/make-call')
    }
    
    return (
        <div>
        I am the successfully user home page
        <button className='btn btn-primary' onClick={makeNewCall}>Make a call</button>
        </div>
    )
}

export default UserComponent
