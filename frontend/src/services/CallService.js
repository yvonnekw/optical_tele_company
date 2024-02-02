import axios from "axios";

const headers = {
    'Content-Type': 'application/json'
  }

const REST_API_BASE_URL = 'http://localhost:8000';

export const listCalls = () => axios.get(REST_API_BASE_URL + '/calls', {
    headers: headers
});

  

export const makeCall = (call) => axios.post(REST_API_BASE_URL + "/calls/make/call", call, {
        headers: headers
    });
  

