import { useState } from "react";
import { register } from "../../services/authService";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    empId: "",
    name: "",
    email: "",
    password: "",
    role: "",
    bandLevel: "",
    managerId: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      ...formData,
      empId: formData.empId.trim(),
      name: formData.name.trim(),
      email: formData.email.trim(),
      password: formData.password,
      role: formData.role.trim().toUpperCase(),
      bandLevel: formData.bandLevel.replace(/\s+/g, ""),
      managerId: formData.managerId.trim(),
    };

    try {
      await register(payload);

      alert("Registration Successful ✅");
      navigate("/");
    } catch (err) {
      console.error(err);
      alert(
        err.response?.data?.message ||
        "Registration Failed ❌"
      );
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-100 via-white to-blue-100 px-4">

      <div className="w-full max-w-lg bg-white rounded-2xl shadow-2xl p-8">

        <h2 className="text-3xl font-bold text-center mb-6">
          Employee Registration
        </h2>

        <form onSubmit={handleSubmit} className="space-y-4">

          {/* EMP ID */}
          <input
            name="empId"
            placeholder="Employee ID"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
          />

          {/* NAME */}
          <input
            name="name"
            placeholder="Name"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
          />

          {/* EMAIL */}
          <input
            name="email"
            type="email"
            placeholder="Email"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
          />

          {/* PASSWORD */}
          <input
            name="password"
            type="password"
            placeholder="Password"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
          />

          {/* ROLE DROPDOWN */}
          <select
            name="role"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
            value={formData.role}
          >
            <option value="">Select Role</option>
            <option value="ADMIN">ADMIN</option>
            <option value="MANAGER">MANAGER</option>
            <option value="EMPLOYEE">EMPLOYEE</option>
          </select>

          {/* BAND LEVEL DROPDOWN */}
          <select
            name="bandLevel"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
            value={formData.bandLevel}
          >
            <option value="">Select Band Level</option>
            <option value="Band1">Band 1</option>
            <option value="Band2">Band 2</option>
            <option value="Band3">Band 3</option>
            <option value="Band4">Band 4</option>
            <option value="Band5">Band 5</option>
          </select>

          {/* MANAGER ID */}
          <input
            name="managerId"
            placeholder="Manager ID"
            onChange={handleChange}
            className="w-full border p-3 rounded-lg"
          />

          {/* BUTTON */}
          <button
            type="submit"
            className="w-full bg-indigo-600 text-white p-3 rounded-lg hover:bg-indigo-700"
          >
            Register
          </button>

        </form>

        <button
          onClick={() => navigate("/")}
          className="w-full mt-4 border border-indigo-500 text-indigo-600 p-3 rounded-lg"
        >
          Back to Login
        </button>

      </div>
    </div>
  );
}