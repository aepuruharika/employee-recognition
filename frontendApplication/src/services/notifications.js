import axios from "axios";

const API_URL = "http://localhost:8086/api/notification"; 

// Get notifications for a user
export const getNotifications = (userId) => {
  return axios.get(`${API_URL}/${userId}`);
};

// Send a notification
export const sendNotification = (data) => {
  return axios.post(`${API_URL}/send`, data);
};

// Delete a notification
export const deleteNotification = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};