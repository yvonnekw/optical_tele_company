import React from 'react';
import { useNavigate } from 'react-router-dom';

const DashboardComponent = () => {
  const navigate = useNavigate();

  const makeNewCall = () => {
    navigate('/make-call');
  };

  return (
    <div className="container mt-5">
      <h2>Welcome to Your Dashboard</h2>
      <p>
        This is a sample dashboard page. You can perform various actions and view information here.
      </p>

      <div className="card mt-4">
        <div className="card-body">
          <h5 className="card-title">Make a Call</h5>
          <p className="card-text">
            Click the button below to make a new call. Record the details and duration of the call.
          </p>
          <button className="btn btn-primary" onClick={makeNewCall}>
            Make a Call
          </button>
        </div>
      </div>

      {/* Add more cards or sections for additional functionality */}
    </div>
  );
};

export default DashboardComponent;
