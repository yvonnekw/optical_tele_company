import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
//import { useAuth } from '../hooks/useAuth';
import { useAuth } from '../hooks/useAuth';

const LoginComponent = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');
  const navigate = useNavigate();

  const [errors, setErrors] = useState({
        username:'',
        password:''
   })

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

   //const { login } = useAuth();
  
   const { login } = useAuth();

  const handleLogin = async (e) => {
    e.preventDefault();
    if (validateForm()) {
        const user = { username, password };
    try {
      const userData = await login(user);
      console.log("from the login page", userData);
    } catch (error) {
      console.error("Error logging in:", error);
      setErrMsg("An error occurred while logging in");
    }
  } else {
    setErrMsg("Username or password incorrect");
  }

/*
    try {
      const response = await loginUser({ username, password });
      const token = response?.data?.token;
      console.log("The token the ui : " + token)
      if (token) {
        // Store token in localStorage or wherever you manage authentication state
        localStorage.setItem('token', token);
        // Redirect to dashboard or any other authorized page
        navigate('/dashboard');
      } else {
        setErrMsg('Username or password incorrect');
      }
    } catch (error) {
      console.error('Error logging in:', error);
      setErrMsg('An error occurred while logging in');
    }*/
  };

  function validateForm(){
        let valid = true;
        //speard of data to copy object 
        const errorsCopy = {... errors}

        if(username.trim()){
            errorsCopy.username = '';
        } else {
            errorsCopy.username = 'username is required';
            valid = false;
        }

        if(password.trim()){
            errorsCopy.password = '';
        } else {
            errorsCopy.password = 'password is required';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }


  return (
     <div className='container'>
        <br /> <br />
        <div className='row'>
          {errMsg && <div className="alert alert-danger">{errMsg}</div>}
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            <h2 className='text-center'>Login here</h2>
            <div className='card-body'>
            <form onSubmit={handleLogin}> {/* Attach onSubmit to the form element */}
              <div className='form-group mb-2'>
              <label htmlFor="username" className="text-center">
                Username:
              </label>
              <input
                type="text"
                id="username"
                value={username}
                className="form-control"
                onChange={handleUsernameChange}
                required
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">
                Password:
              </label>
              <input
                type="password"
                id="password"
                value={password}
                className="form-control"
                onChange={ handlePasswordChange}
                required
              />
            </div>
            <button type="submit" className='btn btn-success'>Login</button> {/* Move the button inside the form */}
          </form>
        </div>
      </div>
      </div>
    </div>
  );
};

export default LoginComponent;