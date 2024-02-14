import axios from "axios";
//import jwt_decode from "jwt-decode";
import { jwtDecode }  from "jwt-decode";
//import jwt_decode from "jwt-decode";


const headers = {
  "Content-Type": "application/json",
};

const REST_API_BASE_URL = "http://localhost:8000";

export const registerUser = (user) =>
  axios.post(REST_API_BASE_URL + "/auth/register", user, {
    headers: headers,
  });

export const loginUser = async (user) => {
  try {
    const response = await axios.post(REST_API_BASE_URL + "/auth/login", user, {
      headers: headers,
    });
    console.log("Response data:", response.data); // Log the response data
    const token = response?.data?.token;
    const username = response?.data?.username;
    console.log("The token:", token);
    console.log("The username :", username);
    return response; // Return the entire response object
  } catch (error) {
    console.error("Error logging in:", error);
    throw error;
  }
};

export const getUsername = async () => {
  const token = localStorage.getItem("token"); // Retrieve the token from local storage

  if (isLoggedIn) {
    //
    try {
      // Make a request to the backend to fetch the username
      const response = await axios.get(REST_API_BASE_URL + "/auth/username", {
        headers: {
          ...headers,
         //Authorization: `Bearer ${localStorage.getItem("token")}`, // Attach token to request headers
          Authorization: `Bearer ${token}`, // Attach token to request headers
        },
      });
      // Extract and return the username from the response data

      console.log("the getUsername " + response.data.username);
      return response.data.username;
    } catch (error) {
      console.error("Error fetching username:", error);
      // Handle error
      throw error; // Rethrow error for the caller to handle
    }
  } else {
    console.error("token has expired!");
  }
};

export const isLoggedIn = () => {
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
/*
const headers = {
    'Content-Type': 'application/json'
  }
  

const REST_API_BASE_URL = "http://localhost:8000";

export const registerUser = (user) => axios.post(REST_API_BASE_URL+"/auth/register", user, {
    headers: headers
});

export const loginUser = (user) => axios.post(REST_API_BASE_URL+"/auth/login", user , {
    headers: headers
});

export const getUsername = () => {
  // Function implementation here
};

// UserService.js
//export const loginUser = async (userData) => {
  // Function implementation
//};

export const isLoggedIn = () => {
  const token = localStorage.getItem("token");
  return !!token; // Returns true if token exists, false otherwise
};
*/