import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();

  const role = localStorage.getItem("role");
  const userId = localStorage.getItem("userId");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
    window.location.reload();
  };

  const isLoggedIn = userId !== null;

  return (
    <nav className="bg-gradient-to-r from-indigo-600 to-indigo-500 text-white shadow-md px-6 py-3 flex items-center justify-between">

      <h1 className="text-lg font-bold">AppreciateX</h1>

      <div className="flex gap-6 text-sm font-medium">

        {/* SHOW ONLY IF NOT LOGGED IN */}
        {!isLoggedIn && (
          <>
            <Link to="/">Login</Link>
            <Link to="/register">Register</Link>
          </>
        )}

        {/* SHOW AFTER LOGIN */}
        {isLoggedIn && (
          <>
            <Link to="/give-points">Appreciate</Link>
            <Link to="/leaderboard">Leaderboard</Link>
            <Link to="/rewards">Rewards</Link>
            <Link to="/notifications">Notifications</Link>
            <Link to="/profile">Profile</Link>

            <button onClick={handleLogout} className="hover:text-gray-200">
              Logout
            </button>
          </>
        )}

        {/* MANAGER DASHBOARD */}
        {role === "MANAGER" || role === "ADMIN" ? (
          <Link to="/manager">Manager Dashboard</Link>
        ) : null}

      </div>
    </nav>
  );
}