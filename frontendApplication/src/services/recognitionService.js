import axios from "axios";

const API_URL = "http://localhost:8082/api/recognition"; 

// Give recognition
export const givePoints = (data) => {
  return axios.post(`${API_URL}/give`, data);
};

// Get leaderboard
export const getLeaderboard = () => {
  return axios.get(`${API_URL}/leaderboard`);
};

// Get recognitions for a user
export const getUserRecognitions = (userId) => {
  return axios.get(`${API_URL}/${userId}`);
};

// Get all recognitions
export const getAllRecognitions = () => {
  return axios.get(`${API_URL}/all`);
};
// Get recognitions given by given Band Level
export const getLeaderboardByBandLevel = (bandLevel) => {
  return axios.get(`${API_URL}/leaderboard/${bandLevel}`);
};