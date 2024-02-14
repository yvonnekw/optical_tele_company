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

// export const getCallReceiversForUser = (username) => axios.get
   // try {
  export const getCallReceiversForUser = (username) =>
    axios.get(
      `${REST_API_BASE_URL}/callreceiver/phone-numbers?username=${username}`,
      {
        headers: headers,
      }
    );
     // return response.data;
   // } catch (error) {
      //throw error; // You might want to handle errors more gracefully in your actual application
  //  }
 // },

  // ... other functions ...
//};

export const checkPhoneNumberExists = (username, phoneNumber) =>
  axios.get(
    `${REST_API_BASE_URL}/callreceiver/phone-numbers?username=${username}&telephone=${phoneNumber}`,
    {
      headers: headers,
    }
  );


  

