import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
//import { useAuth } from '../hooks/useAuth';
//import { useAuth } from '../hooks/useAuth';
import { loginUser2, loginUser, getUsername2 } from '../../services/UserService';
//import { jwtDecode } from 'jwt-decode';
import jwt_decode from "jwt-decode";
import AuthProvider, { AuthContext } from './AuthProvider';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errMsg, setErrMsg] = useState('');

  /*
  const [login, setLogin] = useState({
    username: "",
    password: ""
  })
*/
  //const 
  const navigate = useNavigate();

  const {handleLogin} = useContext(AuthContext)

  const handleInputChange = (e) => {
    
    setLogin({ ...login, [e.target.name] : e.target.value})
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    const user = { username, password };
    //console.log("login Data " + login.username)
    const success = await loginUser2(user)
    //console.log("success " + success)
   // console.log("success username authorities " + success?.data?.authorities)
    // console.log("Response data:", success.data); // Log the response data
    //const token = success?.data?.token;
   // const username = success?.data?.username;
   // console.log("The token:", token);
    //console.log("The username :", username);
    //userData.data.user.username
    if (success) {
      const token = success.token
      handleLogin(token);
      // const token = success.data.token
      //const token = success
      //const decodedToken = jwtDecode(token)
      //localStorage.setItem("token", token)
      //localStorage.setItem("userId", decodedToken.sub)
      //localStorage.setItem("userRole", decodedToken.roles.join(","))
      //localStorage.setItem("username", decodedToken)
     // let userId = localStorage.getItem("userId")
      //console.log("new username returned " + userId)
      //let usern = localStorage.getItem("userId")
     // console.log("new username returned " + usern)
     navigate("/profile")
     // window.location.reload()
    } else {
      setErrMsg("Invalid username or password. Please try again.")
    }
    setTimeout(() => {
      setErrMsg("")
    }, 4000)
  }


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
  
   //const { login } = useAuth();

  const handleLogin2 = async (e) => {
      e.preventDefault();
    if (validateForm()) {
        const user = { username, password };
        try {
          //const userData = await login(user);
             const userData = await loginUser(user);
          
            console.log("from the login page", userData);
            // Check if userData and userData.data are not null/undefined
            if (userData && userData.data && userData.data.user) {
                // Access the username from the response data with null checks
              console.log("from the login page username ", userData.data.user.username);
              //getUsername2();
              
            } else {
                console.error("Response data is invalid:", userData);
                setErrMsg("An error occurred while logging in");
            }
        } catch (error) {
            console.error("Error logging in:", error);
            setErrMsg("An error occurred while logging in");
        }
    } else {
        setErrMsg("Username or password incorrect");
    }
    /*
    e.preventDefault();
    if (validateForm()) {
        const user = { username, password };
    try {
      const userData = await login(user);
      console.log("from the login page", userData);
      console.log("from the login page username ", userData?.data?.user?.username);
    } catch (error) {
      console.error("Error logging in:", error);
      setErrMsg("An error occurred while logging in");
    }
  } else {
    setErrMsg("Username or password incorrect");
  }
*/
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
     <section className='container col-6 mt-5 mb-5'>
      {errMsg && <p className='alert alert-danger'>{ errMsg }</p>}
        <div className='row'>
          {errMsg && <div className="alert alert-danger">{errMsg}</div>}
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            <h2 className='text-center'>Login here</h2>
            <div className='card-body'>
            <form onSubmit={handleSubmit}>
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
                 //onChange={handleInputChange}
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
                  onChange={handlePasswordChange}
                  //onChange={ handleInputChange}
                required
              />
              </div>
              <div className="mb-3">
                <button 
                  type="submit"
                  className='btn btn-success'
                  style={{marginRight : "10px"}}
                >
                  Login
                </button>
                <span style={{ marginLeft: "10px" }}>
                  Not registered yet?<Link to={"/register"}> Register here</Link>
                </span>
             </div>
            </form>
        </div>
      </div>
      </div>
    </section>
  );
};

export default Login;