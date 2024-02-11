import React, { useEffect } from 'react'
import { useState } from 'react'
import { getPhoneNumbers,  addReceiver} from '../../../utils/ApiFunctions'

const CallReceiverSelector = ({ handlePhoneNumberInputChange, newCall}) => {
    const [phoneNumbers, setPhoneNumbers] = useState([""])
    const [showPhoneNumberInput, setShowPhoneNumberInput] = useState(false)
    const [newPhoneNumber, setNewPhoneNumber] = useState("")
    const [username, setUsername] = useState("")

    useEffect(() => {
         async function fetchData() {
        try {
            const response = await getPhoneNumbers('yodalpinky1');
            setPhoneNumbers(response);
            console.log("phone numbers ", response.data);
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

    const handleNewPhoneNumberInputChange = (e) => {
        setNewPhoneNumber(e.target.value);
    }
    
    const handleNewAddPhoneNumber = async () => {

        setUsername("yodalpinky1")
         if (newPhoneNumber !== "") {
            try {
                const success = await addReceiver(newPhoneNumber, username);
                console.log( "successfully added a Call Receiver " + success)
                if (success) {
                    setPhoneNumbers([...phoneNumbers, newPhoneNumber]);
                    setNewPhoneNumber("");
                    setShowPhoneNumberInput(false);
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
            {phoneNumbers.length > 0 && (
                <div>
                    <select
                        id="phoneNumber"
                        name="phoneNumber"
                        value={newCall.phoneNumber}
                        onChange={(e) => {
                            if (e.target.value === "Add New") {
                                setShowPhoneNumberInput(true)
                            } else {
                                handlePhoneNumberInputChange(e)
                            }
                        }}>
                        <option value={""}>Select a call receiver phone number</option>
                        <option value={"Add New"}>Add New</option>
                        {phoneNumbers.map((telephone, index) => (
                            <option key={index} value={telephone}>
                                {telephone}
                            </option>
                        ))}
                    </select>
                    {showPhoneNumberInput && (
                        <div className='input-group'>
                            <input
                                className='form-control'
                                type='text'
                                placeholder='Enter new call receiver phone number'
                                onChange={handleNewPhoneNumberInputChange}
                            />
                            <button className='btn btn-success' type='button' onClick={handleNewAddPhoneNumber}>
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
