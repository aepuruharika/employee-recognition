import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./components/Home/Home";

import Profile from "./components/Home/Profile";



import ManagerDashboard from "./components/Dashboard/ManagerDashboard";
import Navbar from "./components/Navbar";

import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";

import GivePoints from "./components/Recognition/GivePoints";
import Leaderboard from "./components/Recognition/Leaderboard";

import Rewards from "./components/Dashboard/Rewards";
import Notifications from "./components/Dashboard/Notifications";

function App() {
  return (
    <BrowserRouter>

      <Navbar />

      <Routes>

        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route path="/give-points" element={<GivePoints />} />
        <Route path="/leaderboard" element={<Leaderboard />} />

        <Route path="/rewards" element={<Rewards />} />
        <Route path="/notifications" element={<Notifications />} />
        
        <Route path="/home" element={<Home />} />

        <Route path="/profile" element={<Profile />} />  
         <Route
          path="/manager"
          element={
            (localStorage.getItem("role") === "MANAGER"||
            localStorage.getItem("role") === "ADMIN")
              ? <ManagerDashboard />
              : <h2>Access Denied</h2>
          }
        />
      </Routes>

    </BrowserRouter>
  );
}

export default App;