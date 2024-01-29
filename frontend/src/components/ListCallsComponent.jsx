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
        <h2 className='text-center'>List of calls</h2>
        <button className='btn btn-primary'>Make a call</button>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Call Id</th>
                    <th>Call Start Time</th>
                    <th>Call End Time</th>
                </tr>
            </thead>
            <tbody>
                {
                      calls.map(call =>
                        <tr key={call.id}>
                            <td>{call.callId}</td>
                            <td>{call.startTime}</td>
                            <td>{call.endTime}</td>
                        </tr>
                        
                        )
                }
                <tr>

                </tr>
            </tbody>
        </table>
    
    </div>
  )
}

export default ListCallsComponent

