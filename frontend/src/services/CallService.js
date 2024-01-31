import axios from "axios";

const headers = {
    'Content-Type': 'application/json'
  }

const REST_API_BASE_URL = 'http://localhost:8000/calls';

export const listCalls = () => axios.get(REST_API_BASE_URL, {
    headers: headers
});

  

export const makeCall = (call) => axios.post(REST_API_BASE_URL, call, {
        headers: headers
    });
  

