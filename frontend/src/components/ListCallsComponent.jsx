import React, {useEffect, useState} from 'react'
import { listCalls } from '../services/CallService'


const ListCallsComponent = () => {
//defines the variables in a functional component
    const [calls, setCalls] = useState([])

    useEffect(() => {
        listCalls().then((response) => {
            setCalls(response.data);

        }).catch(error => {
            console.error(error);
        })

    }, [])


  return (
    <div className='container'>
        <br /> <br />
        <div className='row'></div>
          <div className='card col-md-6 offset-md-3 offset-md-3'>
              <h2 className='text-center'>Call list</h2>
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
                                    {
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
                                </tbody>
                            </table>
            </div>
    </div>
  )
}

export default ListCallsComponent

