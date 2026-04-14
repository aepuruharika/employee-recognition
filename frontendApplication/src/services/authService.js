import axios from "axios";

const API_URL = "http://localhost:8081/api/users";

// Register user
export const register = (data) => {
  return axios.post(`${API_URL}/register`, data);
};

// Login user
export const login = (data) => {
  return axios.post(`${API_URL}/login`, data);
};

// Get all users 
export const getAllUsers = () => {
  return axios.get(`${API_URL}/all`);
};