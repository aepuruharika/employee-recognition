import axios from "axios";

const API_URL = "http://localhost:8083/api/rewards";

// Assign reward
export const assignReward = (data) => {
  return axios.post(`${API_URL}/assign`, data);
};

// Get rewards for a specific user
export const getUser = (userId) => {
  return axios.get(`${API_URL}/${userId}`);
};

// Get all rewards
export const getAllRewards = () => {
  return axios.get(`${API_URL}/get-all`);
};

// Delete reward
export const deleteReward = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};