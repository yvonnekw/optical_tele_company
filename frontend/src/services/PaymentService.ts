import axios from "axios";
import { basicHeader, REST_API_BASE_URL } from './ApiUtils'

export const makePayment = async (paymentBody: any) => {
  try {
    const response = await axios.post(
      `${REST_API_BASE_URL}/payments/payment`,
      paymentBody,
      {
        headers: basicHeader,
      }
    );
    return response.data;
  } catch (error) {
    throw new Error(`Error making payment: ${error.message}`);
  }
};

/*
export const makePayment = (paymentBody) =>
  axios.post(REST_API_BASE_URL + "/payments/payment", paymentBody, {
    headers: basicHeader,
  });
*/