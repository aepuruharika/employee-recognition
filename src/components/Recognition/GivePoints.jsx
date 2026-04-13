import { useEffect, useState } from "react";
import { givePoints } from "../../services/recognitionService";
import { getAllUsers } from "../../services/authService";

export default function GivePoints() {
  const [points, setPoints] = useState("");
  const [message, setMessage] = useState("");

  const [allEmployees, setAllEmployees] = useState([]);
  const [search, setSearch] = useState("");
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  useEffect(() => {
    fetchEmployees();
  }, []);


  const fetchEmployees = async () => {
  try {
    const res = await getAllUsers();

    const loggedInUserId = localStorage.getItem("userId");

    const filtered = (res.data || []).filter(
      (emp) => emp.empId !== loggedInUserId
    );

    setAllEmployees(filtered);

  } catch (err) {
    console.error(err);
  }
};

  const filteredEmployees = allEmployees.filter((emp) =>
  (emp?.name || "").toLowerCase().includes(search.toLowerCase()) ||
  (emp?.empId || "").toLowerCase().includes(search.toLowerCase())
);

  const handleSubmit = async (e) => {
  e.preventDefault();

  if (!selectedEmployee) {
    alert("Please select an employee");
    return;
  }

  if (!points || isNaN(points)) {
    alert("Enter valid points");
    return;
  }

  const senderId = localStorage.getItem("userId"); 

  const data = {
    senderId: senderId,
    receiverId: selectedEmployee.empId,
    points: Number(points),   
    message: message.trim(),
  };

  console.log("GIVE POINTS PAYLOAD:", data);

  try {
    await givePoints(data);

    alert("Recognition Sent Successfully 🚀");

    setSearch("");
    setSelectedEmployee(null);
    setPoints("");
    setMessage("");

  } catch (err) {
    console.error("GIVE POINTS ERROR:", err.response || err);
    alert(
  err.response?.data?.message ||
  err.response?.data ||
  "Error sending recognition ❌"
);
  }
};

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-white to-blue-50 flex items-center justify-center p-6">

      <div className="w-full max-w-lg bg-white rounded-2xl shadow-2xl border border-gray-100 p-8">

        {/* Header */}
        <div className="text-center mb-6">
          <h2 className="text-3xl font-bold text-gray-800">
            🎯 Appreciate Colleagues
          </h2>
          <p className="text-sm text-gray-500 mt-1">
          Recognize your teammates with points and meaningful feedback
          </p>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">

          {/* Employee Search */}
          <div className="relative">

            <input
              placeholder="Search employee..."
              value={search}
              onChange={(e) =>{setSearch(e.target.value);
                setSelectedEmployee(null);
              }}
              className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
            />
            

{search && filteredEmployees.length > 0 && !selectedEmployee && (
              <div className="absolute z-10 bg-white border w-full mt-1 rounded-lg shadow max-h-60 overflow-y-auto">

                {filteredEmployees.map((emp) => (
                  <div
                    key={emp.empId}
                    onClick={() => {
                      setSelectedEmployee(emp);
                      setSearch(emp.name);
                    }}
                    className="p-3 hover:bg-indigo-50 cursor-pointer"
                  >
                    <p className="font-medium">{emp.name}</p>
                    <p className="text-xs text-gray-500">{emp.empId}</p>
                  </div>
                ))}

              </div>
            )}

          </div>

          {/* Points */}
          <input
            placeholder="Points (e.g. 10, 20, 50)"
            value={points}
            onChange={(e) => setPoints(e.target.value)}
            className="w-full border p-3 rounded-lg focus:ring-2 focus:ring-indigo-400 outline-none"
          />

          {/* Message */}
          <textarea
            placeholder="Write appreciation message..."
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            className="w-full border p-3 rounded-lg h-28 resize-none focus:ring-2 focus:ring-indigo-400 outline-none"
          />

          {/* Submit */}
          <button
            type="submit"
            className="w-full bg-indigo-600 text-white p-3 rounded-lg hover:bg-indigo-700 transition font-medium"
          >
            Send Recognition 🚀
          </button>

        </form>

      </div>

    </div>
  );
}