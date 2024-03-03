import axios from "axios";
//import jwt_decode from "jwt-decode";
//import { jwtDecode }  from "jwt-decode";
//import jwt_decode from "jwt-decode";
import jwt_decode from "jwt-decode"; //

export const basicHeader = {
  "Content-Type": "application/json",
};

export const api = axios.create({
  baseURL: "http://localhost:8000",
});

export const REST_API_BASE_URL = "http://localhost:8000";

export const getLoginHeader = () => {
  const token = localStorage.getItem("token");
  return {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
};
