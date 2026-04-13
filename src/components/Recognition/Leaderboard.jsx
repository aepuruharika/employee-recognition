import { useEffect, useState } from "react";
import {
  getLeaderboard,
  getLeaderboardByBandLevel
} from "../../services/recognitionService";

export default function Leaderboard() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [bandFilter, setBandFilter] = useState("ALL");

  useEffect(() => {
    fetchLeaderboard();
  }, []);

  const fetchLeaderboard = async () => {
    try {
      const res = await getLeaderboard();
      setData(res.data);
      setFilteredData(res.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleFilter = async (value) => {
    setBandFilter(value);

    try {
      let res;

      if (value === "ALL") {
        res = await getLeaderboard();
      } else {
        res = await getLeaderboardByBandLevel(value);
      }

      setFilteredData([...res.data]);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-blue-50 py-10 px-4 text-center">

      {/* TITLE */}
      <h2 className="text-3xl font-bold text-gray-800 mb-6">
        🏆 Leaderboard
      </h2>

      {/* FILTER */}
      <div className="mb-6">
        <select
          value={bandFilter}
          onChange={(e) => handleFilter(e.target.value)}
          className="px-4 py-2 rounded-lg border border-gray-300 shadow-sm focus:outline-none focus:ring-2 focus:ring-indigo-400"
        >
          <option value="ALL">All Bands</option>
          <option value="Band1">Band1</option>
          <option value="Band2">Band2</option>
          <option value="Band3">Band3</option>
        </select>
      </div>

      {/* TABLE CARD */}
      <div className="max-w-5xl mx-auto bg-white shadow-lg rounded-xl overflow-hidden">

        <table className="w-full border-collapse">

          {/* HEADER */}
          <thead>
            <tr className="bg-gradient-to-r from-blue-500 to-indigo-500 text-white">
              <th className="p-3 text-sm font-semibold">Rank</th>
              <th className="p-3 text-sm font-semibold">User ID</th>
              <th className="p-3 text-sm font-semibold">Name</th>
              <th className="p-3 text-sm font-semibold">Points</th>
            </tr>
          </thead>

          {/* BODY */}
          <tbody>
            {filteredData.map((user, index) => (
              <tr
                key={index}
                className="hover:bg-gray-50 transition border-b"
              >
                <td className="p-3 text-gray-700">{user.rank}</td>
                <td className="p-3 text-gray-700">{user.userId}</td>
                <td className="p-3 text-gray-700">{user.userName}</td>
                <td className="p-3 text-gray-700 font-semibold">
                  {user.totalPoints}
                </td>
              </tr>
            ))}
          </tbody>

        </table>

      </div>
    </div>
  );
}