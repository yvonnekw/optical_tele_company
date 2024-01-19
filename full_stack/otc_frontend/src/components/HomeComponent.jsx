import React from 'react';
import { useNavigate } from 'react-router-dom';

const HomeComponent = () => {


  const navigator = useNavigate();

  function login(){
      navigator('/login')
  }
  
  return (
    <div>
      I am the home page
      <button className='btn btn-primary' onClick={login}>Login</button>
     
    </div>
  )
}

export default HomeComponent
