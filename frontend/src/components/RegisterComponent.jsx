import React, {useEffect, useState} from 'react';
import { registerUser }from '../services/UserService';
import { useNavigate } from 'react-router-dom';

const RegisterComponent = () => {


   const[firstName, setFirstName] = useState('')
   const[lastName, setLastName] = useState('')
   const[emailAddress, setEmailAddress] = useState('')
   const[telephoneNumber, setTelephoneNumber] = useState('')

   const [errors, setErrors] = useState({
        firstName:'',
        lastName:'',
        emailAddress:'',
        telephoneNumber:''
   })

   const navigate = useNavigate();

    function saveUser (e){
        e.preventDefault();
        
        if(validateForm()){
            const user = {firstName, lastName, emailAddress, telephoneNumber}
            console.log(user)
    
            registerUser(user).then((response) =>{
                console.log(response.data)
    
                navigate('/login')
            })
        } 
    }

    function validateForm(){
        let valid = true;
        //speard of data to copy object 
        const errorsCopy = {... errors}

        if(firstName.trim()){
            errorsCopy.firstName = '';
        } else {
            errorsCopy.firstName = 'First name is required';
            valid = false;
        }

        if(lastName.trim()){
            errorsCopy.lastName = '';
        } else {
            errorsCopy.lastName = 'Last name is required';
            valid = false;
        }

        if(emailAddress.trim()){
            errorsCopy.emailAddress = '';
        } else {
            errorsCopy.emailAddress = 'Email address required';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }

    return (
        <div className='container'>
        <br /> <br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2 className='text-center'>Register here</h2>
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>First Name</label>
                            <input
                                type='text'
                                placeholder='Enter user First Name'
                                name='firstName'
                                value={firstName}
                                className={`form-control ${errors.firstName ? 'is-invalid': '' }`}
                                onChange={(e) => setFirstName(e.target.value)}
                                >
                            </input>
                            {errors.firstName && <div className='invalid-feedback'>{ errors.firstName }</div> }
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name</label>
                            <input
                                type='text'
                                placeholder='Enter user Last Name'
                                name='lastName'
                                value={lastName}
                                className={`form-control ${errors.lastName ? 'is-invalid': '' }`}
                                onChange={(e) => setLastName(e.target.value)}
                                >
                            </input>
                            {errors.lastName && <div className='invalid-feedback'>{ errors.lastName }</div> }
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Email Address</label>
                            <input
                                type='text'
                                placeholder='Enter your email address'
                                name='email'
                                value={emailAddress}
                                className={`form-control ${errors.emailAddress ? 'is-invalid': '' }`}
                                onChange={(e) => setEmailAddress(e.target.value)}
                                >
                            </input>
                            {errors.emailAddress && <div className='invalid-feedback'>{ errors.emailAddress }</div> }
                        </div>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Telephone</label>
                            <input
                                type='number'
                                placeholder='Enter your telephone number'
                                name='phone'
                                value={telephoneNumber}
                                className={`form-control ${errors.telephoneNumber ? 'is-invalid': '' }`}
                                onChange={(e) => setTelephoneNumber(e.target.value)}
                                >
                            </input>
                            {errors.telephoneNumber && <div className='invalid-feedback'>{ errors.telephoneNumber }</div> }
                        </div>
                        <button className='btn btn-success' onClick={saveUser}>Submit</button>
                    </form>
                </div>

            </div>
        </div>

    </div>
        
    )
}
export default RegisterComponent
