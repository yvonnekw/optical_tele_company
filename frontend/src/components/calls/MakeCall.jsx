import React, { useState, useEffect, useContext } from 'react';
//import { Link } from 'react-router-dom';
import moment from 'moment';
import Select from 'react-select';
import { makeCall,  getCallReceiversForUser, checkPhoneNumberExists} from '../../services/CallService';
import { loginUser, loginUser2, getUsername } from '../../services/UserService'; // Import loginUser and isLoggedIn from UserService
import CallReceiverSelector from '../common/CallReceiverSelector';
import AuthProvider, { AuthContext } from '../auth/AuthProvider';
import { getCallsByUsername } from '../../services/CallService';

const MakeCall = () => {

  const currentUser = localStorage.getItem("userId")
// State for start and end times
const [startTime, setStartTime] = useState('');
const [endTime, setEndTime] = useState('');
const [totalTime, setTotalTime] = useState('');
const [callCost, setCallCost] = useState(0);
const [discount, setDiscount] = useState(0);
const [totalCost, setTotalCost] = useState(0);
const [netCost, setNetCost] = useState(0);
const [grossCost, setGrossCost] = useState(0);
const [taxAmount, setTaxAmount] = useState(0);
const [telephone, setTelephone] = useState('');
const [callReceivers, setCallReceivers] = useState([]);
const [telephones, setTelephones] = useState([]);
const [selectedCallReceiver, setSelectedCallReceiver] = useState('');
const [selectedTelephoneNumber, setSelectedTelephoneNumber] = useState('');
const [username, setUsername] = useState('');
const [password, setPassword] = useState('');
const [errorMessage, setErrorMessage]= useState('');
const [successMessage, setSuccessMessage] = useState('');
  const [calls, setCalls] = useState([]);
  
   const userId = localStorage.getItem("userId");
  const token = localStorage.getItem("token");
  

  const [newCall, setNewCall] = useState({
        startTime:'',
        endTime:'',
        callCost:'',
        discount: '',
        totalCost:'',
        netCost:'',
        grossCost:'',
        taxAmount: '',
        telephone: ''
  })

   const [errors, setErrors] = useState({
        startTime:'',
        endTime:'',
        callCost:'',
        discount: '',
        totalCost:'',
        netCost:'',
        grossCost:'',
        taxAmount:''
   })
  
    const {isLoggedIn} = useContext(AuthContext)
  
  const handleCallInputChange = (e) => {
    const name = e.target.name
    let value = e.target.value
       if (name === "telephone") {
            setSelectedTelephoneNumber(value);
        }
    if (name === "startTime") {
      if (!isNaN(value)) {
        value.parseTime(value)
      } else {
        value = ""
      }
    }
      if (name === "endTime") {
      if (!isNaN(value)) {
        value.parseTime(value)
      } else {
        value = ""
      }
      }
      if (name === "discount") {
      if (!isNaN(value)) {
        value.parseTime(value)
      } else {
        value = ""
      }
    }
    setNewCall({ ...newCall, [name]: value })
  }
  
  const handleTelphoneNumberChange = (selectedOption) => {
    setSelectedTelephoneNumber(selectedOption);
     setNewCall({ ...newCall, telephone: selectedOption.value });
  };

// Rate for the call cost per second
const ratePerSecond = 0.001;

// Tax rate
const taxRate = 0.20;

// Function to handle input change for start time
const handleStartTimeChange = (event) => {
  setStartTime(event.target.value);
};

// Function to handle input change for end time
const handleEndTimeChange = (event) => {
  setEndTime(event.target.value);
};

// Function to handle input change for discount
const handleDiscountInputChange = (event) => {
  setDiscount(event.target.value);
};
  
  useEffect(() => {
    const fetchCalls = async () => {
      try {
        const response = await getCallsByUsername(userId, token);
        setCalls(response);
        console.log("get calls ", calls)
      } catch (error) {
        console.error("Error fetching calls: ", error.message);
        setErrorMessage(error.message);
      }
    };

    fetchCalls();
  }, [userId, token]);

// Function to calculate total time, call cost, and total cost
const calculateTotalTime = () => {
  const parseTime = (time) => {
    const [hours, minutes, seconds] = time.split(':').map(Number);
    return hours * 3600 + minutes * 60 + seconds;
  };

  const startSeconds = parseTime(startTime);
  const endSeconds = parseTime(endTime);

  if (!isNaN(startSeconds) && !isNaN(endSeconds)) {
    const seconds = endSeconds - startSeconds;

    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);

    setTotalTime(`${hours} hours and ${minutes} minutes`);

    const rawCallCost = seconds * ratePerSecond;

    let callCostWithDiscount = rawCallCost;
    if (discount !== '') {
      callCostWithDiscount -= (rawCallCost * (parseFloat(discount) / 100));
    }

    const taxAmount = callCostWithDiscount * taxRate;
    const netCostValue = callCostWithDiscount;
    const grossCostValue = callCostWithDiscount + taxAmount;
    const totalCostValue = grossCostValue;

    setCallCost(callCostWithDiscount.toFixed(2));
    setNetCost(netCostValue.toFixed(2));
    setGrossCost(grossCostValue.toFixed(2));
    setTotalCost(totalCostValue.toFixed(2));
  } else {
    setTotalTime('Invalid time format');
    setCallCost(0);
    setNetCost(0);
    setGrossCost(0);
    setTotalCost(0);
  }
};
  
  const handleSubmit = async (event) => {
    event.preventDefault();

    if (isLoggedIn()) { 
      
    if (validateForm()) {
      calculateTotalTime();

      // setUsername('yodalpinky1')
      const call = {
        startTime: startTime,
        endTime: endTime,
        duration: totalTime,
        costPerMinute: ratePerSecond * 60, // Assuming cost is per minute
        discountForCalls: parseFloat(discount),
        signUpDiscount: 0, // Placeholder, modify as needed
        vat: taxRate,
        netCost: parseFloat(netCost),
        grossCost: parseFloat(grossCost),
        totalCost: parseFloat(totalCost),
        username: currentUser,//'yodalpinky1',//username, //'yodalpinky1',//username,//'yodalpinky1',// await getUsername() ,//'yodalpinky1', // Replace with dynamic username from your app
        telephone: selectedTelephoneNumber // "032456776580"//selectedTelephoneNumber //"032456776580"
      };
      try {
        console.log("Request data " + call.startTime);
        console.log("telephone number for the call " + call.telephone);
        console.log("username number for the call " + call.username);
        console.log("telephone number for the call " + selectedTelephoneNumber.toString());
        //const call = {startTime, endTime}
        const isValid = await checkPhoneNumberExists(currentUser, selectedTelephoneNumber);//('yodalpinky1', selectedTelephoneNumber);
        console.log(isValid)
        const success = await makeCall(call);
        console.log(success.data);

        if (success !== undefined) {
          setSuccessMessage("A new call has been recorded in the database.")
          setNewCall({ startTime: null, callReceivers: "", endTime: "" })
          setErrorMessage("")
        } else {
          setErrorMessage("Error adding call to the database")
        }

      } catch (error) {
        setErrorMessage(error.message)
      }
      setTimeout(() => {
        setSuccessMessage("")
        setErrorMessage("")
      }, 3000)
    };
  };
  };
  
   function validateForm(){
        let valid = true;
        //speard of data to copy object 
        const errorsCopy = {... errors}

        if(startTime.trim()){
            errorsCopy.startTime = '';
        } else {
            errorsCopy.startTime = 'Start time required';
            valid = false;
        }

        if(endTime.trim()){
            errorsCopy.endTime = '';
        } else {
            errorsCopy.endTime = 'End Time required';
            valid = false;
        }
/*
        if(discount.trim()){
            errorsCopy.discount = '';
        } else {
            errorsCopy.discount = 'Email address required';
            valid = false;
        }*/

        setErrors(errorsCopy);

        return valid;
    }

      return (
    <div className='container'>
      <br /> <br />
      <div className='row'>
        <div className='card col-md-6 offset-md-3 offset-md-3'>
          <h2 className='text-center'>New call</h2>
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-label'>Call Receiver phone number</label>
                <div>
                  <CallReceiverSelector handleTelephoneNumberInputChange={handleCallInputChange} newCall={newCall} />
                </div>
              </div>
              <div className='form-group mb-2'>
                <label className='form-label'>Start Time</label>
                <input
                      type='text'
                      placeholder='Enter start time (HH:mm:ss)'
                      name='startTime'
                      value={startTime}
                      className={`form-control ${errors.startTime ? 'is-invalid' : ''}`}
                      onChange={handleStartTimeChange}//{handleCallInputChange}
                />
              </div>
              <div className='form-group mb-2'>
                <label className='form-label'>End Time</label>
                <input
                  type='text'
                  placeholder='Enter end time (HH:mm:ss)'
                  name='endTime'
                  value={endTime}
                  className={`form-control ${errors.endTime ? 'is-invalid' : ''}`}
                  onChange={handleEndTimeChange}
                />
              </div>
              <div className='form-group mb-2'>
                <label className='form-label'>Discount (%)</label>
                <input
                  type='text'
                  placeholder='Enter discount'
                  name='discount'
                  value={discount}
                  className='form-control'
                  onChange={handleDiscountInputChange} //{handleCallInputChange}
                />
              </div>
              <button className='btn btn-success' onClick={handleSubmit}>
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
      <div className='container'>
        <br /> <br />
        <div className='row'>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
            <h2 className='text-center'>Current Calls</h2>
            <div className='card-body'>
              <table className='table table-striped table-bordered'>
                <thead>
                  <tr>
                    <th>Call ID</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Duration</th>
                    {/* Add more table headers if needed */}
                  </tr>
                </thead>
                 <tbody>
                  {calls.map(call => (
                    <tr key={call.id}>
                      <td>{call.callId}</td>
                      <td>{moment(call.startTime).format('YYYY-MM-DD HH:mm:ss')}</td>
                      <td>{moment(call.endTime).format('YYYY-MM-DD HH:mm:ss')}</td>
                      <td>{call.duration}</td>
                      {/* Add more table cells based on call properties */}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
 
  

   

export default MakeCall
