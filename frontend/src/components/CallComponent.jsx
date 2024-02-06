
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { makeCall,  getCallReceiversForUser} from '../services/CallService';

const CallComponent = () => {
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
  
  const [callReceivers, setCallReceivers] = useState([]);
  const [selectedCallReceiver, setSelectedCallReceiver] = useState('');

  
   useEffect(() => {
    // Fetch call receivers for a user
    const fetchCallReceivers = async () => {
      try {
        const response = await getCallReceiversForUser('yodalpinky1'); // Replace with dynamic username
        setCallReceivers(response.data);
      } catch (error) {
        console.error('Error fetching call receivers:', error);
        // Handle error, show an error message
      }
    };

    fetchCallReceivers();
  }, []); // Empty dependency array to ensure the effect runs only once

  const handleCallReceiverChange = (event) => {
    setSelectedCallReceiver(event.target.value);
  };

// Rate for the call cost per second
const ratePerSecond = 0.01;

// Tax rate
const taxRate = 0.20;

// Function to handle input change for start time
//const handleStartTimeChange = (event) => {
  //setStartTime(event.target.value);
//};

// Function to handle input change for end time
const handleEndTimeChange = (event) => {
  setEndTime(event.target.value);
};

// Function to handle input change for discount
const handleDiscountChange = (event) => {
  setDiscount(event.target.value);
};

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

// Function to handle form submission
const handleSubmit = async (event) => {
  event.preventDefault();
  if(validateForm()){
  calculateTotalTime();

  
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
    username: 'yodalpinky1', // Replace with dynamic username from your app
    telephone: "032456776581"
  };
      try {
        console.log("Request data " + call.startTime);
          //const call = {startTime, endTime}
          const response = await makeCall(call);
          console.log(response.data);
          //console.log(call)

          //makeCall(call).then((response) =>{
            // console.log(response.data)
        //
    // })

        } catch (error) {
          console.error('Error making the call:', error);
          // Handle error, show an error message
        }
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
            <form >
                   <div className="form-group mb-2">
                    <label className="form-label">Call Receiver</label>
                    <select
                      name="callReceiver"
                      value={selectedCallReceiver}
                      className="form-control"
                      onChange={handleCallReceiverChange}
                    >
                      <option value="">Select Call Receiver</option>
                      {callReceivers.map((receiver) => (
                        <option key={receiver.callReceiverId} value={receiver.telephone}>
                          {receiver.firstName} {receiver.lastName} - {receiver.telephone}
                        </option>
                      ))}
                    </select>
                  </div>
                      <div className='form-group mb-2'>
                        <label className='form-label'>Start Time</label>
                        <input
                          type='text'
                          placeholder='Enter start time (HH:mm:ss)'
                          name='startTime'
                          value={startTime}
                          className={`form-control ${errors.startTime ? 'is-invalid': '' }`}
                         onChange={(e) => setStartTime(e.target.value)}
                        />
                      </div>

                      <div className='form-group mb-2'>
                        <label className='form-label'>End Time</label>
                        <input
                          type='text'
                          placeholder='Enter end time (HH:mm:ss)'
                          name='endTime'
                          value={endTime}
                          className={`form-control ${errors.endTime ? 'is-invalid': '' }`}
                          onChange={(e) => setEndTime(e.target.value)}
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
                          onChange={(e) => setDiscount(e.target.value)}
                        />
                      </div>

                      <button className='btn btn-success' onClick={handleSubmit}>
                        Submit
                      </button>

                    {totalTime && (
                      <div className="mt-3">
                        Total Time: {totalTime}
                        Call Cost: ${callCost}
                        <p>Tax: ${taxAmount.toFixed(2)}</p>
                        <p>Net Cost: ${netCost}</p>
                        <p>Gross Cost: ${grossCost}</p>
                        <p>Total Cost: ${totalCost}</p>
                      </div>
                    )}

                    {/* Other form elements go here */}
                  </form>
   </div>
  </div>
</div>
</div>
    )
}

export default CallComponent
