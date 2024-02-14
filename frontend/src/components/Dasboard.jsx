import React from 'react';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const navigate = useNavigate();

  const goToProfile = () => {
    navigate('/profile');
  };

  const goToCallHistory = () => {
    navigate('/call-history');
  };

  const makeNewCall = () => {
    navigate('/make-call');
  };

  return (
    <div className='container'>
      <br /> <br />
      <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          <div className='card-body'>
            <h2>Welcome to Your Dashboard</h2>
            <p>You can perform various actions and view information here.</p>

            <div className="card mt-4">
              <div className="card-body">
                <h5 className="card-title">User Profile</h5>
                <p className="card-text">
                  View and edit your profile information.
                </p>
                <button className='btn btn-primary' onClick={goToProfile}>
                  View Profile
                </button>
              </div>
            </div>

            <div className="card mt-4">
              <div className="card-body">
                <h5 className="card-title">Call History</h5>
                <p className="card-text">
                  View your call history and details of past calls.
                </p>
                <button className='btn btn-primary' onClick={goToCallHistory}>
                  View Call History
                </button>
              </div>
            </div>

            <div className="card mt-4">
              <div className="card-body">
                <h5 className="card-title">Make a Call</h5>
                <p className="card-text">
                  Click the button below to make a new call.
                </p>
                <button className='btn btn-success' onClick={makeNewCall}>
                  Make a Call
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

  /*
  const makeNewCall = () => {
    navigate('/make-call');
  };

  return (
     <div className='container'>
        <br /> <br />
        <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          <div className='card-body'></div>
          <h2>Welcome to Your Dashboard</h2>
          <p>
            You can perform various actions and view information here.
          </p>

          <div className="card mt-4">
            <div className="card-body">
              <h5 className="card-title">Make a Call</h5>
              <p className="card-text">
                Click the button below to make a new call. Record the details and duration of the call.
              </p>
              <button className='btn btn-success' onClick={makeNewCall}>
                Make a Call
              </button>
            </div>
          </div>

      {/* Add more cards or sections for additional functionality 
        </div>
      </div>
    </div>
  );
};

*/

export default Dashboard;
