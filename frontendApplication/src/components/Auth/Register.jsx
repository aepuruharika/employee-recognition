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

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });


    setErrors({
      ...errors,
      [e.target.name]: "",
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setErrors({});

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
      console.error("ERROR:", err.response || err);

      const data = err.response?.data;


      if (data?.details) {
        setErrors(data.details);
      } else {
        alert(data?.message || "Registration Failed ❌");
      }

    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-indigo-100 via-white to-blue-100 px-4">

      <div className="w-full max-w-lg bg-white rounded-2xl shadow-2xl border border-gray-100 p-8">

        <div className="text-center mb-6">
          <h2 className="text-3xl font-bold text-gray-800">
            Employee Registration
          </h2>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">

          {/* Employee ID */}
          <div>
            <input
              name="empId"
              placeholder="Employee ID"
              onChange={handleChange}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            {errors.empId && (
              <p className="text-red-500 text-sm mt-1">{errors.empId}</p>
            )}
          </div>

          {/* Name */}
          <div>
            <input
              name="name"
              placeholder="Name"
              onChange={handleChange}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            {errors.name && (
              <p className="text-red-500 text-sm mt-1">{errors.name}</p>
            )}
          </div>

          {/* Email */}
          <div>
            <input
              name="email"
              type="email"
              placeholder="Email"
              onChange={handleChange}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            {errors.email && (
              <p className="text-red-500 text-sm mt-1">{errors.email}</p>
            )}
          </div>

          {/* Password */}
          <div>
            <input
              name="password"
              type="password"
              placeholder="Password"
              onChange={handleChange}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            {errors.password && (
              <p className="text-red-500 text-sm mt-1">{errors.password}</p>
            )}
          </div>

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


          {/* Band Level */}
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

          {/* Manager ID */}
          <div>
            <input
              name="managerId"
              placeholder="Manager ID"
              onChange={handleChange}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            {errors.managerId && (
              <p className="text-red-500 text-sm mt-1">{errors.managerId}</p>
            )}
          </div>

          {/* Submit */}
          <button
            type="submit"
            disabled={loading}
            className="w-full bg-indigo-600 text-white p-3 rounded-lg hover:bg-indigo-700 transition disabled:opacity-50"
          >
            {loading ? "Registering..." : "Register"}
          </button>

        </form>

        <button
          onClick={() => navigate("/")}
          className="w-full mt-4 border border-indigo-500 text-indigo-600 p-3 rounded-lg hover:bg-indigo-50 transition"
        >
          Back to Login
        </button>

      </div>
    </div>
  );
}