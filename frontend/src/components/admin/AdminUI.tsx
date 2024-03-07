import React from 'react'
import { Link } from 'react-router-dom'

import ListCallsComponent from '../calls/ListAllCalls'


const AdminUI: React.FC = () => {
  return (
    <>
      <section className='container mt-5'>
        <h2>Welcome to the Admin Panel</h2>
      </section>
      <Link to={"/calls"}>
        Manage Calls
      </Link>
      <div>
        <ListCallsComponent />
      </div>
    </>
  );
}

export default AdminUI;

/*
const AdminUI = () => {
  return (
    <>
    <section className='container mt-5'>
      <h2>Welcome to the Admin Panel</h2>
    </section>
      <Link to={"/calls"}>
        Manage Calls
      </Link>



    <div>
      <ListCallsComponent />
      </div>
      
      </>
  )
}

export default AdminUI
*/