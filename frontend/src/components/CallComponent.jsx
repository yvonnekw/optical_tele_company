
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { makeCall } from '../services/CallService';

const CallComponent = () => {
// State for start and end times
const [startTime, setStartTime] = useState('');
const [endTime, setEndTime] = useState('');
const [totalTime, setTotalTime] = useState('');
const [callCost, setCallCost] = useState(0);
const [discount, setDiscount] = useState('');
const [totalCost, setTotalCost] = useState(0);
const [netCost, setNetCost] = useState(0);
const [grossCost, setGrossCost] = useState(0);
const [taxAmount, setTaxAmount] = useState(0);

// Rate for the call cost per second
const ratePerSecond = 0.01;

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
const handleSubmit = (event) => {
  event.preventDefault();
  calculateTotalTime();

  event.preventDefault();

  const call = {startTime, endTime}
  console.log(call)

  makeCall(call).then((response) =>{
      console.log(response.data)

  })
};

 
  return (
    <div>
        The call page

        <form onClick={handleSubmit}>
      <div className='form-group mb-2'>
        <label className='form-label'>Start Time</label>
        <input
          type='text'
          placeholder='Enter start time (HH:mm:ss)'
          name='startTime'
          value={startTime}
          className='form-control'
          onChange={handleStartTimeChange}
        />
      </div>

      <div className='form-group mb-2'>
        <label className='form-label'>End Time</label>
        <input
          type='text'
          placeholder='Enter end time (HH:mm:ss)'
          name='endTime'
          value={endTime}
          className='form-control'
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
          onChange={handleDiscountChange}
        />
      </div>

      <button type="submit" className="btn btn-primary">
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

    )
}

export default CallComponent
