import { useEffect, useState } from "react";
import { getLeaderboard } from "../../services/recognitionService";
import { Bar } from "react-chartjs-2";
import { Box, Typography } from "@mui/material";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from "chart.js";

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

export default function ManagerDashboard() {
  const [chartData, setChartData] = useState({ labels: [], datasets: [] });

  useEffect(() => {
    getLeaderboard().then(res => {
      const labels = res.data.map(u => u.name);
      const points = res.data.map(u => u.points);
      setChartData({
        labels,
        datasets: [{ label: "Points", data: points, backgroundColor: "rgba(75,192,192,0.6)" }]
      });
    });
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-blue-50 p-6">

      <div className="max-w-6xl mx-auto">

        {/* Header */}
        <div className="text-center mb-8">
          <h2 className="text-3xl font-bold text-gray-800">
            Manager Dashboard
          </h2>
          <p className="text-sm text-gray-500 mt-1">
            Employee Performance Analytics
          </p>
        </div>

        {/* KPI Cards */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-8">

          <div className="bg-white p-5 rounded-xl shadow border text-center">
            <p className="text-gray-500 text-sm">Top Performer</p>
            <p className="text-xl font-bold text-indigo-600">
              {chartData.labels?.[0] || "-"}
            </p>
          </div>

          <div className="bg-white p-5 rounded-xl shadow border text-center">
            <p className="text-gray-500 text-sm">Total Employees</p>
            <p className="text-xl font-bold text-green-600">
              {chartData.labels?.length || 0}
            </p>
          </div>

          <div className="bg-white p-5 rounded-xl shadow border text-center">
            <p className="text-gray-500 text-sm">System Status</p>
            <p className="text-xl font-bold text-yellow-600">
              Active
            </p>
          </div>

        </div>

        {/* Chart */}
        <div className="bg-white p-6 rounded-2xl shadow border">
          <h3 className="text-lg font-semibold text-gray-700 mb-4">
            Points Overview
          </h3>
          <Bar data={chartData} />
        </div>

      </div>
    </div>
  );
}