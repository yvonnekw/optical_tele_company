import axios from "axios";
import { basicHeader, REST_API_BASE_URL } from '../services/ApiUtils'

export const makePayment = (paymentBody) =>
  axios.post(REST_API_BASE_URL + "/payments/payment", paymentBody, {
    headers: basicHeader,
  });
