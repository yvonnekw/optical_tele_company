
import { createContext, useContext, useMemo } from 'react'; // Change userMemo to useMemo
import { useLocalStorage } from "./useLocalStorage";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

const headers = {
  "Content-Type": "application/json",
};

const REST_API_BASE_URL = "http://localhost:8000";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null); // Store user data including token
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (user) => {
    try {
      // Perform authentication request to backend API
      const response = await axios.post(REST_API_BASE_URL + "/auth/login", user, {
        headers: headers,
      });
      // Assuming the backend returns user data including token upon successful authentication
      const userData = await response.data.token;

      console.log("from the login auth " + userData)

      // Store user data including token in local storage or state
      setUser(userData);

      // Redirect to dashboard or any other authorized page
      navigate("/dashboard");
      return userData;
    
    } catch (error) {
      console.error("Error logging in:", error);
      // Handle authentication error
    }
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  // Function to decode the user token and retrieve user information
  const decodeToken = (token) => {
    // Example: Decode token using a library like jwt-decode
    // Replace jwtDecode with the appropriate library for decoding JWT tokens
    const decodedToken = jwtDecode(token);
    return decodedToken;
  };

  const getUsernameFromToken = () => {
    if (user && user.token) {
      const decodedToken = decodeToken(user.token);
      return decodedToken.username;
    }
    return null;
  };

  const value = {
    user,
    login,
    logout,
    getUsernameFromToken, // Provide a function to get username from token
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => useContext(AuthContext);

/*

const headers = {
  "Content-Type": "application/json",
};

const REST_API_BASE_URL = "http://localhost:8000";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null); // Store user data including token
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (user) => {
    try {
      // Perform authentication request to backend API
      //const response = await authenticateUser(credentials); // Example function to authenticate user

      const response = await axios.post(REST_API_BASE_URL + "/auth/login", user, {
        headers: headers,
      });
      // Assuming the backend returns user data including token upon successful authentication
      const userData = response.data;

      // Store user data including token in local storage or state
      setUser(userData);

      // Redirect to dashboard or any other authorized page
      navigate("/dashboard");
       // return response; // Return the entire response object
    } catch (error) {
      console.error("Error logging in:", error);
      // Handle authentication error
    }
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  // Function to decode the user token and retrieve user information
  const decodeToken = (token) => {
    // Example: Decode token using a library like jwt-decode
    // Replace jwtDecode with the appropriate library for decoding JWT tokens
    const decodedToken = jwtDecode(token);
    return decodedToken;
  };

  const getUsernameFromToken = () => {
    if (user && user.token) {
      const decodedToken = decodeToken(user.token);
      return decodedToken.username;
    }
    return null;
  };

  const value = {
    user,
    login,
    logout,
    getUsernameFromToken, // Provide a function to get username from token
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

/*
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null);
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (data) => {
    setUser(data);
    navigate("/dashboard");
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  const value = {
    user,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

*/
/*
export const useAuth = () => {
  return useContext(AuthContext);
};*/

//export const useAuth = () => useContext(AuthContext);



/*import { createContext, useContext, userMemo } from 'react';
import { useLocalStorage } from "./useLocalStorage";
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null);
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (data) => {
    setUser(data);
    navigate("/dashboard");
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  const value = {
    user,
    login,
    logout,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  return useContext(AuthContext);
};



/*

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useLocalStorage("user", null);
  const navigate = useNavigate();

  // call this function when you want to authenticate the user
  const login = async (data) => {
    setUser(data);
    navigate("/dashboard");
  };

  // call this function to sign out logged in user
  const logout = () => {
    setUser(null);
    navigate("/", { replace: true });
  };

  const value = useMemo(
    () => ({
      user,
      login,
      logout,
    }),
    [user]
  );
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  return useContext(AuthContext);
};
/*
/*
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const login = async (credentials) => {
    // Your login logic here
    setIsLoggedIn(true);
  };

  const logout = () => {
    // Your logout logic here
    setIsLoggedIn(false);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

*/

/*
import React, { createContext, useState, useEffect } from "react";
import { loginUser } from "../services/UserService";

export const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Retrieve user from localStorage on component mount
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser(JSON.parse(storedUser));
    }
  }, []);

const login = async (userData) => {
  try {
    const response = await loginUser(userData);
    const token = response?.data?.token;
    if (token) {
      localStorage.setItem("token", token);
      // Update user state if needed (e.g., setUser(response.data.user))
    } else {
      console.error("No token found in login response");
      // Handle missing token error
    }
  } catch (error) {
    console.error("Error logging in:", error);
    // Handle login error
  }
};

  const logout = () => {
    // Implement logout logic here (clear user data, token, etc.)
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;

*/