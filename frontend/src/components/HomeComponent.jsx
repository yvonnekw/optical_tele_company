import React from 'react';
import { useNavigate, Link} from 'react-router-dom';


const HomeComponent = () => {


  const navigator = useNavigate();

  function login(){
      navigator('/login')
  }

  function register(){
    navigator('/register')
}
  
  return (
    <div>
      I am the home page
      <div className="container mt-5">
      <h1 className="mb-4">Welcome to Optical Telephone Company</h1>
      <p className="lead">
      Optical Telephone Company platform where you can initiate and manage calls easily.
      </p>

      <div className="my-4">
        <h2>Get Started</h2>
        <p>New to Your App Name? Register now to get started!</p>
        <Link to="/register" className="btn btn-primary">Register</Link>
      </div>

      <div className="my-4">
        <h2>Already have an account?</h2>
        <p>Log in to access your dashboard and initiate calls.</p>
        <Link to="/login" className="btn btn-outline-primary">Login</Link>
      </div>
    </div>

     
    </div>
  )
}

export default HomeComponent
