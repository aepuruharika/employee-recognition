import { useState } from "react";
import { login } from "../../services/authService";
import { useNavigate } from "react-router-dom";

export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

   const handleSubmit = async (e) => {
    e.preventDefault();

    try {
        const payload = {
            email: email.trim(),
            password: password,
        };

        console.log("LOGIN PAYLOAD:", payload);

        const res = await login(payload);

        console.log("FULL RESPONSE:", res.data);

        
        const user = res.data?.user || res.data;

        localStorage.setItem("userId", user.empId);
        localStorage.setItem("role", user.role);

        alert("Login Successful");

        navigate("/home");

    } catch (err) {
        console.error("LOGIN ERROR FULL:", err.response || err);

        alert(
            err.response?.data?.message ||
            "Invalid email or password ❌"
        );
    }
};

    return (
        <div className="h-screen w-full flex items-center justify-center bg-gradient-to-br from-indigo-100 via-white to-indigo-200">

            <div className="w-full max-w-md bg-white p-8 rounded-2xl shadow-2xl border">

                <h2 className="text-3xl font-bold text-center text-gray-800 mb-2">
                    Employee Login
                </h2>

                <p className="text-center text-gray-500 mb-6">

                </p>

                <form onSubmit={handleSubmit} className="space-y-4">

                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="w-full border p-3 rounded-lg"
                    />

                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full border p-3 rounded-lg"
                    />

                    <button
                        type="submit"
                        className="w-full bg-indigo-600 text-white p-3 rounded-lg hover:bg-indigo-700"
                    >
                        Login
                    </button>

                </form>

                <button
                    onClick={() => navigate("/register")}
                    className="w-full mt-4 border p-3 rounded-lg text-indigo-600"
                >
                    Create Account
                </button>

            </div>
        </div>
    );
}