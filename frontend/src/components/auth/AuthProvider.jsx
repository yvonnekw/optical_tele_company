

import React, { createContext, useState } from 'react';
import jwt_decode from "jwt-decode";



export const AuthContext = createContext({
    user: null,
    handleLogin: (token) => {},
    handleLogout: () => {},
}) 

const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null)

    const handleLogin = (token) => {
        try {
            const decodedToken = jwt_decode(token)
             //console.log("decoded Token ", decodedToken); 
            localStorage.setItem("userId", decodedToken.sub)
            localStorage.setItem("userRole", decodedToken.scope);
            localStorage.setItem("token", token)
            setUser(decodedToken)
        } catch (e) {
             console.error('Error decoding token:', e);  
        }
    }

    const handleLogout = () => {
 
        localStorage.removeItem("userId")
        localStorage.removeItem("userRole")
        localStorage.removeItem("token")
        setUser(null)
    }

   const isLoggedIn = () => {
 // const token = localStorage.getItem("token");
 // return !!token; // Returns true if token exists, false otherwise
  
   const token = localStorage.getItem("token");
    if (!token) {
      return false; // No token found
    }

  // Decode the token to get its payload
  const decodedToken = jwtDecode(token);
  
  // Check if the token expiration time is in the past
  const isExpired = Date.now() >= decodedToken.exp * 1000;
  
  return !isExpired; // Returns true if token exists and is not expired, false otherwise

};

    return (
        <AuthContext.Provider value={{user, handleLogin, handleLogout}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider
