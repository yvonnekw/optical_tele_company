import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser }from '../services/UserService';


const LoginComponent = () => {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');

  const navigate = useNavigate();

  useEffect(() => {
    setErrMsg('');
  }, []);

  const [errors, setErrors] = useState({
    username: '',
    password: '',
  });

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = async (event) => {
    event.preventDefault();

    if (validateForm()) {
      const login = { username, password };
      try {
        const response = await loginUser(login);
        const token = response?.data?.token;
        const user = response?.data?.user;
  
       if (token  && user ) {
          navigate('/dashboard');
        }
       else {
        setErrMsg('Username or password incorrect');
      }
    } catch (error) {
      if (error?.response) {
        if (error.response.status === 400) {
          setErrMsg('Username or password missing');
        } else if (error.response.status === 401) {
          setErrMsg('Unauthorised');
        } else {
          setErrMsg(`Login failed: ${error.response.status}`);
        }
      } else {
        setErrMsg('No Server Response');
      }
    }
  };
}

  function validateForm() {
    let valid = true;
    const errorsCopy = { ...errors };

    if (username.trim()) {
      errorsCopy.username = '';
    } else {
      errorsCopy.username = 'Username is required';
      valid = false;
    }
    if (password.trim()) {
      errorsCopy.password = '';
    } else {
      errorsCopy.password = 'Password is required';
      valid = false;
    }

    setErrors(errorsCopy);

    return valid;
  }

  return (
  
    <div className="container">
      <br /> <br />
        <div className='row'></div>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2 className='text-center'>Login here</h2>
                <div className='card-body'>
                  {/* Display error message */}
                  {errMsg && (
                    <div className="alert alert-danger" role="alert">
                      {errMsg}
                    </div>
                  )}
        <form>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">
              Username:
            </label>
            <input
              type="text"
              id="username"
              value={username}
              className={`form-control ${errors.username ? 'is-invalid' : ''}`}
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
              className={`form-control ${errors.password ? 'is-invalid' : ''}`}
              onChange={handlePasswordChange}
              required
            />
          </div>
          <button className='btn btn-success' onClick={handleLogin}>Login</button>
        
        </form>
      </div>
   </div>
</div>
  )
};


export default LoginComponent;
