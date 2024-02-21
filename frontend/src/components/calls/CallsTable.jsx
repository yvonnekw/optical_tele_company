import React, { useEffect, useState } from 'react';
import { getCallsByUsername } from '../../services/CallService';


const CallsTable = ({ userId }) => {
    //defines the variables in a functional component
    const [calls, setCalls] = useState([])
    //const [username, setUsername] = useState('')

    //setUsername('yodalpinky1')

    /*
    useEffect(() => {
        getCallsByUsername(username).then((response) => {
            console.log("response data " + response.data)
            setCalls(response.data);

        }).catch(error => {
            console.error(error);
        })

    }, [username])

    */
    
      useEffect(() => {
        // Fetch calls data when the component mounts or when the username changes
        getCallsByUsername(userId)
            .then((response) => {
                setCalls(response.data); // Set calls data from the response
            })
            .catch(error => {
                console.error(error);
            });
    }, [userId]);

  return (
    <div className='container'>
        <br /> <br />
        <div className='row'></div>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
              <h2 className='text-center'>Call Summary</h2>
                <div className='card-body'></div>
                    <table className='table table-striped table-bordered'>
                            <thead>
                                    <tr>
                                        <th>Call Id</th>
                                        <th>Start Time</th>
                                        <th>End Time</th>
                                        <th>Duration</th>
                                        <th>TotalTime</th>
                                        <th>Cost Per Minute</th>
                                        <th>Discount For Call</th>
                                        <th>SignUp Discount</th>
                                        <th>VAT</th>  
                                        <th>Net Cost</th>
                                        <th>Gross Cost</th>
                                        <th>Total Cost</th>
                                    </tr>
                                </thead>
                                <tbody>
                      {/* {
                                        calls.map(call =>
                                            <tr key={call.id}>
                                                <td>{call.callId}</td>
                                                <td>{call.startTime}</td>
                                                <td>{call.endTime}</td>
                                                <td>{call.duration}</td>
                                                <td>{call.totalTime}</td>
                                                <td>{call.costPerMinute}</td>
                                                <td>{call.discountForCalls}</td>
                                                <td>{call.signUpDiscount}</td>
                                                <td>{call.vat}</td>
                                                <td>{call.netCost}</td>
                                                <td>{call.grossCost}</td>
                                                <td>{call.totalCost}</td>
                                            </tr>
                                            
                                            )
                                    }
                                    <tr>

                      </tr>
                                */}
                    
                      {calls.length > 0 ? ( // Check if calls array is not empty before mapping
                            calls.map(call =>
                                <tr key={call.id}>
                                    {/* Table body cells */}
                                </tr>
                            )
                        ) : (
                            <tr>
                                <td colSpan="12">No calls found</td>
                                  </tr>
                                     )}
                                </tbody>
                            </table>
            </div>
    </div>
  )
}

export default CallsTable
