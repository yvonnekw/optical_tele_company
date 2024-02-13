import axios from "axios";

const headers = {
  "Content-Type": "application/json",
};

export const api = axios.create({
    baseURL : "http://localhost:8000"
})

export async function registerUser(firstName, lastName, emailAddress, telephone) {

    const formData = new FormData();
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("emailAddress", emailAddress);
    formData.append("telephone", telephone);
   // formData.append("authorities", []);

    try {
      const response = await api.post(`/auth/register`, formData, { headers: headers });
      if (response.status === 200) {
        return true;
      } else {
        return false;
      }
    } catch (error) {
      console.error("Error registering user:", error);
      throw new Error("Error registering user");
    }
  
  /*
  const formData = new FormData();
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("emailAddress", emailAddress);
    formData.append("telephone", telephone);
    formData.append("authorities", []);

  const response = await api.post(`/auth/register`,
    { formData },
   {headers: headers})
      if (response.status === 200) {
          return true
              
      } else {
          return false;
      }
  
  */

    }

    export async function addReceiver(telephone, username) {
      try {
        const response = await api.post(
          `/callreceiver/add/reciever`,
          {
            telephone: telephone,
            username: username,
          },
          { headers: headers }
        );
        if (response.status === 200) {
          return true;
        } else {
          // If the response status is not 200, log an error and return false
          console.error(
            "Error adding new receiver - Unexpected response status:",
            response.status
          );
          return false;
        }
      } catch (error) {
        console.error("Error adding new receiver:", error);
        throw new Error("Error adding new receiver");
      }
    }



export async function getTelephoneNumbers(username) {

    try {
      // const response = await api.get("/phone-numbers");
      const response = await api.get(
        `/callreceiver/phone-numbers?username=${username}`,
        {
          headers: headers,
        }
      );
      return response.data;
    } catch (error) {
      console.error("Error getting telephone numbers:", error);
      return []; // Return an empty array in case of error
    }
}