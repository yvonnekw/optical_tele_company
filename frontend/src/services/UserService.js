import axios from "axios";

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
