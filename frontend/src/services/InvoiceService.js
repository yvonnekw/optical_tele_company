import axios from "axios";
import { basicHeader, REST_API_BASE_URL, api } from "../services/ApiUtils";
/*
const basicHeader = {
  "Content-Type": "application/json",
};

export const api = axios.create({
  baseURL: "http://localhost:8000",
});

const REST_API_BASE_URL = "http://localhost:8000";

export const getLoginHeader = () => {
  const token = localStorage.getItem("token");
  return {
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };
};
*/
export async function invoice(invoiceBody) {
  try {
    const response = await api.post(
      REST_API_BASE_URL + "/invoices/create-invoice",
      invoiceBody,
      {
        headers: basicHeader,
      }
    )
    console.log("invoice create from invoice service ", response.data);
    return response.data;
  } catch (error) {
    throw error;
  }
};


export async function getAllInvoices() {
  try {
    const response = await api.get(
      REST_API_BASE_URL + "/invoices/get-all-invoice",
      {
        headers: basicHeader,
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}

export async function searchInvoiceById(invoiceId) {
  try {
    const response = await api.get(
      REST_API_BASE_URL + `/invoices/
      ${invoiceId}`,
      {
        headers: basicHeader,
      }
    );
    return response.data;
  } catch (error) {
    throw error;
  }
}



