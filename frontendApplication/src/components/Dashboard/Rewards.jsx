import { useEffect, useState } from "react";
import { getAllRewards } from "../../services/rewardService";
import { getUser } from "../../services/rewardService";

export default function Rewards() {
  const [rewards, setRewards] = useState([]);
  const [loading, setLoading] = useState(true);
  /*
    useEffect(() => {
      getAllRewards()
        .then(res => {
          setRewards(res.data);
          setLoading(false);
        })
        .catch(err => {
          console.error(err);
          setLoading(false);
        });
    }, []);
  */

  useEffect(() => {
    const userId = localStorage.getItem("userId");

    if (!userId) {
      console.error("No userId found in localStorage");
      setLoading(false);
      return;
    }

    getUser(userId)
      .then(res => {
        setRewards([res.data]);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setLoading(false);
      });
  }, []);



  const getBadgeColor = (points) => {
    if (points >= 100) return "bg-purple-100 text-purple-600";
    if (points >= 50) return "bg-green-100 text-green-600";
    return "bg-yellow-100 text-yellow-600";
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-blue-50 p-6">

      <div className="max-w-5xl mx-auto">

        {/* Header */}
        <div className="text-center mb-8">
          <h2 className="text-3xl font-bold text-gray-800">
            🏆 Rewards & Badges
          </h2>
          <p className="text-sm text-gray-500 mt-1">
            Employee achievements and milestone recognition
          </p>
        </div>

        {/* Loading */}
        {loading && (
          <div className="text-center text-gray-500">
            Loading rewards...
          </div>
        )}

        {/* Empty */}
        {!loading && rewards.length === 0 && (
          <div className="bg-white p-8 rounded-2xl shadow border text-center">
            <p className="text-gray-500">No rewards available yet</p>
          </div>
        )}

        {!loading && rewards.length > 0 && (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

            {rewards.map((reward, index) => (
              <div key={index} className="bg-white border rounded-2xl shadow p-5">

                {/* Badge */}
                <div className="flex items-center gap-3 mb-3">
                  <div className="w-10 h-10 flex items-center justify-center bg-yellow-100 text-yellow-600 rounded-full text-lg">
                    🏅
                  </div>

                  <h4 className="text-lg font-semibold">
                    Reward
                  </h4>
                </div>

                {/* Details */}
                <div className="text-sm text-gray-600 space-y-1">
                  <p>
                    <span className="font-medium">Employee:</span> {reward?.userId}
                  </p>

                  <p>
                    <span className="font-medium">Badge Name:</span> {reward?.badgeName}
                  </p>
                  <p>
                    <span className="font-medium">Points Achieved:</span> {reward?.milestonePoints}
                  </p>
                </div>

              </div>
            ))}

          </div>
        )}

      </div>
    </div>
  );
}