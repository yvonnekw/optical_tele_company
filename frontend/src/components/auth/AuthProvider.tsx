import React, { ReactNode, createContext, useState } from 'react';
import jwt_decode from "jwt-decode";

interface User {
  sub: string;
  scope: string;
}

interface DecodedToken {
  sub: string;
  scope: string;
  exp: number;
}

interface AuthContextType {
  user: User | null;
  handleLogin: (token: string) => void;
  handleLogout: () => void;
  isLoggedIn: () => boolean;
}

export const AuthContext = createContext<AuthContextType>({
  user: null,
  handleLogin: () => {},
  handleLogout: () => {},
  isLoggedIn: () => false,
});

const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);

  const handleLogin = (token: string) => {
    try {
      const decodedToken: DecodedToken = jwt_decode(token);
      localStorage.setItem("userId", decodedToken.sub);
      localStorage.setItem("userRole", decodedToken.scope);
      localStorage.setItem("token", token);
      setUser(decodedToken);
    } catch (e) {
      console.error("Error decoding token:", e);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("userId");
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    setUser(null);
  };

  const isLoggedIn = () => {
    const token = localStorage.getItem("token");
    if (!token) {
      return false;
    }
    try {
      const decodedToken: DecodedToken = jwt_decode(token);
      const isExpired = Date.now() >= decodedToken.exp * 1000;
      return !isExpired;
    } catch (error) {
      console.error("Error decoding token:", error);
      return false;
    }
  };

  return (
    <AuthContext.Provider
      value={{ user, handleLogin, handleLogout, isLoggedIn }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;

/*
interface User {
  sub: string;
  scope: string;
}
interface DecodedToken {
  sub: string; // Example property, adjust as needed
  scope: string; // Example property, adjust as needed
  exp: number;
}

interface AuthContextType {
  user: User | null;
  handleLogin: (token: string) => void;
  handleLogout: () => void;
  isLoggedIn: () => boolean;
}

export const AuthContext = createContext<AuthContextType>({
  user: null,
  handleLogin: () => {},
  handleLogout: () => {},
  isLoggedIn: () => false,
});

const AuthProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);

  const handleLogin = (token: string) => {
    try {
      const decodedToken = jwt_decode<User>(token);
      localStorage.setItem("userId", decodedToken.sub);
      localStorage.setItem("userRole", decodedToken.scope);
      localStorage.setItem("token", token);
      setUser(decodedToken);
    } catch (e) {
      console.error("Error decoding token:", e);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("userId");
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    setUser(null);
  };

  const isLoggedIn = () => {
    const token = localStorage.getItem("token");
    if (!token) {
      return false;
    }
    const decodedToken = jwt_decode<User>(token);
    const isExpired = Date.now() >= decodedToken.exp * 1000;
    return !isExpired;
  };


  return (
    <AuthContext.Provider
      value={{ user, handleLogin, handleLogout, isLoggedIn }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;

*/

/*
export const AuthContext = createContext({
    user: null,
    handleLogin: (token) => {},
    handleLogout: () => {},
    isLoggedIn: () => {}
}) 

const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null)

    const handleLogin = (token) => {
        try {
            const decodedToken = jwt_decode(token)
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
 // return !!token; // Returns true if token exists, false otherwise
  
   const token = localStorage.getItem("token");
       if (!token) {
        
      return false; // No token found
    }
  // Decode the token to get its payload
  const decodedToken = jwt_decode(token);
  
  // Check if the token expiration time is in the past
  const isExpired = Date.now() >= decodedToken.exp * 1000;
  
  return !isExpired; // Returns true if token exists and is not expired, false otherwise

};

    return (
        <AuthContext.Provider value={{user, handleLogin, handleLogout, isLoggedIn}}>
            {children}
        </AuthContext.Provider>
    )
}

export default AuthProvider

*/
