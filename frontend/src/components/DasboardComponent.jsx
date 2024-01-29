import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';




const DashboardComponent = () => {


    const navigator = useNavigate();

    function makeNewCall(){
        navigator('/make-call')
    }

    return (
        <div className='container'>
            I am the user page
            <button className='btn btn-primary' onClick={makeNewCall}>Make a call</button>
            
        </div>
    )
}

export default DashboardComponent
