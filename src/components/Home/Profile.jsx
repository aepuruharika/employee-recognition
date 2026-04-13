import { useEffect, useState } from "react";

export default function Profile() {
  const [user, setUser] = useState({});

  useEffect(() => {
    setUser({
      userId: localStorage.getItem("userId"),
      role: localStorage.getItem("role"),
    });
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-6">

      <div className="max-w-md mx-auto bg-white shadow rounded-xl p-6">

        <h2 className="text-2xl font-bold mb-4">👤 Profile</h2>

        <p><b>User ID:</b> {user.userId}</p>
        <p><b>Role:</b> {user.role}</p>

      </div>

    </div>
  );
}