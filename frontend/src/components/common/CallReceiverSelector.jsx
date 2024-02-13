import React, { useEffect } from 'react'
import { useState } from 'react'
import { getTelephoneNumbers,  addReceiver} from '../../../utils/ApiFunctions'
import { getUsername } from '../../services/UserService'; // Import loginUser and isLoggedIn from UserService

const CallReceiverSelector = ({ handleTelephoneNumberInputChange, newCall}) => {
    const [telephoneNumbers, setTelephoneNumbers] = useState([""])
    const [showTelephoneNumberInput, setShowTelephoneNumberInput] = useState(false)
    const [newTelephoneNumber, setNewTelephoneNumber] = useState("")
    const [selectedTelephoneNumber, setSelectedTelephoneNumber] = useState('');
    const [username, setUsername] = useState("")

    useEffect(() => {
         async function fetchData() {
        try {
            const response = await getTelephoneNumbers('yodalpinky1');
            setTelephoneNumbers(response);
            console.log("telephone numbers ", response.data);
        } catch (error) {
            console.error("Error fetching phone numbers:", error);
        }
    }
    fetchData();
        /*
        async function fetchData() {
            const response = await getPhoneNumbers('yodalpinky1').then((data) => {
                setPhoneNumbers(response)
            });
            console.log("phone numbers " +response.data)
        }
        fetchData();*/
    }, [])

    const handleNewTelephoneNumberInputChange = (e) => {
        setNewTelephoneNumber(e.target.value);
    }

    /*
   const handleTelphoneNumberChange = (selectedValue) => {
        setSelectedTelephoneNumber(selectedValue);
        //handleTelephoneNumberInputChange({ target: { name: 'telephone', value: selectedValue } });
    };*/
    
    const handleAddNewTelephoneNumber = async () => {

        //const username = await getUsername(); // Fetch the username after successful login
       // console.log('Username after login:', username);
        setUsername("yodalpinky1")
         if (newTelephoneNumber !== "") {
            try {
                const success = await addReceiver(newTelephoneNumber, username);
              
                if (success) {
                    console.log( "successfully added a Call Receiver " + success)
                    setTelephoneNumbers([...telephoneNumbers, newTelephoneNumber]);
                    setNewTelephoneNumber("");
                    setShowTelephoneNumberInput(false);
                } else {
                    // Handle unsuccessful response
                    console.error('Error adding new phone number');
                }
            } catch (error) {
                // Handle error
                console.error('Error adding new phone number:', error);
            }
    }
        
        /*
        if (newPhoneNumber !== "") {
            setPhoneNumbers([...phoneNumbers, newPhoneNumber])
            setNewPhoneNumber("")
            setShowPhoneNumberInput(false)
        }*/
    }

    return (
        <>
            {telephoneNumbers.length > 0 && (
                <div>
                    <select
                        id="telephone"
                        name="telephone"
                        value={newCall.telephone}
                        onChange={(e) => {
                            if (e.target.value === "Add New") {
                                setShowTelephoneNumberInput(true)
                            } else {
                               // handleTelphoneNumberChange(e.target.value); // Call the handleTelphoneNumberChange function here
                                ///handleNewTelephoneNumberInputChange(e)
                                handleTelephoneNumberInputChange(e)
                            }
                        }}>
                        <option value={""}>Select a call receiver phone number</option>
                        <option value={"Add New"}>Add New</option>
                        {telephoneNumbers.map((telephone, index) => (
                            <option key={index} value={telephone}>
                                {telephone}
                            </option>
                        ))}
                    </select>
                    {showTelephoneNumberInput && (
                        <div className='input-group'>
                            <input
                                className='form-control'
                                type='text'
                                placeholder='Enter new call receiver phone number'
                                onChange={handleNewTelephoneNumberInputChange}
                            />
                            <button className='btn btn-success' type='button' onClick={handleAddNewTelephoneNumber}>
                                Add
                            </button>
                        </div>
                    )}
                </div>
            )}
        </>
    )
}

export default CallReceiverSelector
