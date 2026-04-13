import { useEffect, useState } from "react";
import { getNotifications } from "../../services/notifications";

export default function Notifications() {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    const userId = localStorage.getItem("userId");

    if (!userId) {
      console.error("User not logged in");
      return;
    }

    getNotifications(userId)
      .then(res => {
        console.log("Notifications:", res.data);
        setNotifications(res.data);
      })
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-blue-50 p-6">

      <div className="max-w-3xl mx-auto">

        {/* Header */}
        <div className="text-center mb-8">
          <h2 className="text-3xl font-bold text-gray-800">
            🔔 Notifications
          </h2>
          <p className="text-sm text-gray-500 mt-1">
            Your latest updates and alerts
          </p>
        </div>

        {/* Content */}
        {notifications.length === 0 ? (
          <div className="bg-white p-8 rounded-2xl shadow border text-center">
            <p className="text-gray-500">No notifications yet</p>
          </div>
        ) : (
          <div className="space-y-4">

            {notifications.map((n, index) => (
              <div
                key={index}
                className="bg-white border shadow-sm rounded-xl p-4 hover:shadow-md transition"
              >

                <p className="text-gray-800 font-medium">
                  {n.message}
                </p>

                <p className="text-xs text-gray-500 mt-2">
                  {n.date}
                </p>

              </div>
            ))}

          </div>
        )}

      </div>
    </div>
  );
}
