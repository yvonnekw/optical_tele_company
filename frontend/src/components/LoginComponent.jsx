import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser }from '../services/UserService';


const LoginComponent = () => {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigator = useNavigate();

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

  const handleLogin = async (event) => {
    event.preventDefault();

    if(validateForm()){
      const login = {username, password}
      console.log(login)

      loginUser(login).then((response) =>{
          console.log(response.data)
      })

   
    // If login is successful, navigate to another page
    navigator('/user')
   
    };

    function validateForm(){
      let valid = true;
      //speard of data to copy object 
      const errorsCopy = {... errors}

      if(username.trim()){
          errorsCopy.username = '';
      } else {
          errorsCopy.username = 'Username is required';
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

    };
}

  return (
    <div className="container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div className="mb-3">
          <label htmlFor="username" className="form-label">Username:</label>
          <input type="text" 
          id="username" 
          value={username} 
          className={`form-control ${errors.username ? 'is-invalid': '' }`}
          onChange={handleUsernameChange} required />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password:</label>
          <input type="password" 
          id="password" 
          value={password}
          className={`form-control ${errors.password ? 'is-invalid': '' }`}
          onChange={handlePasswordChange} required />
        </div>
        <button type="submit" className="btn btn-primary">Login</button>
      </form>
    </div>
  );


}

export default LoginComponent;
