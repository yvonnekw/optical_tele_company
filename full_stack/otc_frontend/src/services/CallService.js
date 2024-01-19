import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8000/api/calls';

export const listCalls = () => axios.get(REST_API_BASE_URL);
