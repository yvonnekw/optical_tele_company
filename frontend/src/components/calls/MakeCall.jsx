import React, { useState, useEffect } from 'react';
//import { Link } from 'react-router-dom';
import Select from 'react-select';
import { makeCall,  getCallReceiversForUser, checkPhoneNumberExists} from '../../services/CallService';
import { loginUser, isLoggedIn, getUsername } from '../../services/UserService'; // Import loginUser and isLoggedIn from UserService
import CallReceiverSelector from '../common/CallReceiverSelector';
import { useAuth } from '../../hooks/useAuth'
import CallsTable from './CallsTable';

const MakeCall = () => {
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
  
/*
  // Fetch call receivers for a user
  useEffect(() => {

     if (isLoggedIn()) {
      const fetchCallReceivers = async () => {
        try {
          const response = await getCallReceiversForUser(username);
          setSelectedCallReceiver(response.data); // Update to select appropriate receiver data
        } catch (error) {
          console.error('Error fetching call receivers:', error);
        }
      };
    
  
        fetchCallReceivers();
      }
    },
    []);
  
  */
  
/*
 // Fetch phone numbers based on selected call receiver
  useEffect(() => {
      if (isLoggedIn() && selectedCallReceiver) {
        const fetchPhoneNumbers = async () => {
          try {
            if (selectedCallReceiver) {
              const response = await getDistinctPhoneNumbersForUser(selectedCallReceiver.username);
              setPhoneNumbers(response.data);
            }
          } catch (error) {
            console.error('Error fetching phone numbers:', error);
          }
        };
  
      fetchPhoneNumbers();
    }
  }, [selectedCallReceiver]);

* /
  
const handleCallReceiverChange = (selectedOption) => {
    setSelectedCallReceiver(selectedOption);
  };


  */

    
  const handleTelphoneNumberChange = (selectedOption) => {
    setSelectedTelephoneNumber(selectedOption);
     setNewCall({ ...newCall, telephone: selectedOption.value });
  };

// Rate for the call cost per second
const ratePerSecond = 0.001;

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

  //const { getUsernameFromToken } = useAuth();
  //const { decodeToken } = useAuth();
  
// Function to handle form submission
const handleSubmit = async (event) => {
  event.preventDefault();
/*
   if (isLoggedIn()) {
    // If user is logged in, set the username
    const loggedInUsername = await getUsername();
    setUsername(loggedInUsername);
    console.log("login username a " + loggedInUsername);
  } else {
    // Log in the user
    const user = { username, password };
    try {
      const response = await loginUser(user);
      // Upon successful login, store session information
      // For example, you can use localStorage:
      localStorage.setItem('user', JSON.stringify(response.data));
      // Set the username after successful login
      const loggedInUsername = await getUsername();
      setUsername(loggedInUsername);
      console.log("login username b " + loggedInUsername);
      // Now, the user is logged in, you can make the call
    } catch (error) {
      console.error('Error logging in:', error);
      // Handle login error
    }
   }
  */
/*
  if (isLoggedIn()) {
    // If user is logged in, set the username
    setUsername(getUsername());
    console.log("login username a "+ username)
  } else {
    // Log in the user
    const user = { username, password };
    try {
      const response = await loginUser(user);
      // Upon successful login, store session information
      // For example, you can use localStorage:
      localStorage.setItem('user', JSON.stringify(response.data));
      // Set the username after successful login
      setUsername(getUsername());
        console.log("login username b "+ username)
      // Now, the user is logged in, you can make the call
    } catch (error) {
      console.error('Error logging in:', error);
      // Handle login error
    }
  }*/

  

  if (isLoggedIn()) {
   // const tok = await  decodeToken(); // Fetch the username after successful login
      //const usern = await getUsernameFromToken(); // Fetch the username after successful login
    //console.log('Username after login:', usern);
    //console.log('token after login:',tok);
  } else {
      
        // Log in the user
      // const user = { username: 'example', password: 'password' }; // Replace with actual login credentials
    const user = { username, password };
    console.log("user " + user)
      try {
          const response = await loginUser(user);
          // Upon successful login, store session information
          // For example, you can use localStorage:
        
        if (response.data.user !== null) {
          localStorage.setItem('user', JSON.stringify(response.data));

       // const username = await getUsername(); // Fetch the username after successful login
        console.log('Username after login:', username);
          //let usern = (response.data.username)
         // console.log('username returned after login', usern);
          // Now, the user is logged in, you can make the call
         // setUsername(response.data.username);
        } else {
          console.log("login did not happen ")
          return;
        }
        } catch (error) {
          console.error('Error logging in:', error);
          // Handle login error
          return;
        }
    }
      
    if(validateForm()){
      calculateTotalTime();

      setUsername('yodalpinky1')
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
        username: 'yodalpinky1',//username, //'yodalpinky1',//username,//'yodalpinky1',// await getUsername() ,//'yodalpinky1', // Replace with dynamic username from your app
        telephone: selectedTelephoneNumber // "032456776580"//selectedTelephoneNumber //"032456776580"
      };
          try {
            console.log("Request data " + call.startTime);
            console.log("telephone number for the call " + call.telephone);
            console.log("username number for the call " + call.username);
             console.log("telephone number for the call " + selectedTelephoneNumber.toString());
              //const call = {startTime, endTime}
            const isValid = await checkPhoneNumberExists('yodalpinky1', selectedTelephoneNumber);
            console.log(isValid)
              const success = await makeCall(call);
            console.log(success.data);

              if (success !== undefined) {
                setSuccessMessage("A new call has been recored in the database.")
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
                <label className="form-label">Call Receiver phone number</label>
                <div>

                  <CallReceiverSelector handleTelephoneNumberInputChange={handleCallInputChange} newCall={newCall} />

                 { /*
                  <CallReceiverSelector
                    

                   handleCallInputChange={handleCallInputChange}
                    newCall={newCall}
                  
                  />*/}
                </div>
                {/*
                      <Select
                        id="callReceiver"
                        value={selectedCallReceiver}
                        onChange={handleCallReceiverChange}
                        options={callReceivers.map(receiver => ({
                          value: receiver,
                          label: `${receiver.firstName} ${receiver.lastName}`
                        }))}
                      />
                    </div>
                    <div className="form-group mb-2">
                      <label className="form-label">Phone Number</label>
                      <Select
                        id="phoneNumber"
                        value={selectedPhoneNumber}
                        onChange={handlePhoneNumberChange}
                        options={phoneNumbers.map(number => ({ value: number, label: number }))}
                />
                      */}
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
                    

              <CallsTable username={username} />

                    {/* Other form elements go here */}
                  </form>
              </div>
              </div>
            </div>
            </div>
    )
}

export default MakeCall
