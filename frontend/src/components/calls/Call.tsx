import React, { FormEvent, useContext, useEffect, useState } from 'react'
import CallReceiverSelector from '../common/CallReceiverSelector';
import { AuthContext } from '../auth/AuthProvider';
import { checkPhoneNumberExists, enterCall, getCallsByUsername } from '../../services/CallService';
import { Link, LinkProps } from 'react-router-dom';
import moment from 'moment';
import createInvoice from '../invoice/CreateInvoice';
//import { LocationDescriptorObject } from 'history';
import { useNavigate } from 'react-router-dom';



interface CustomLinkProps extends Omit<LinkProps, 'to'> {
    to: {
        pathname: string;
        state: {
            userId: string | null;
            netCost: number;
            invoiceId: string;
        };
    };
}

interface CallFormErrors {
    startTime: string;
    endTime: string;
    callCost: string;
    discount: string;
    totalCost: string;
    netCost: string;
    grossCost: string;
    taxAmount: string;
}

interface CallType {
    id: number; // Assuming id is of type number
    callId: string;
    startTime: string;
    endTime: string;
    duration: number;
    costPerMinute: number;
    discountForCalls: number;
    grossCost: number;
    vat: number;
    netCost: number;
    // Add more properties if needed
}

const Call: React.FC = () => {

    const [newCall, setNewCall] = useState<{
        startTime: string;
        endTime: string;
        callCost: string;
        discount: string;
        totalCost: string;
        netCost: string;
        grossCost: string;
        taxAmount: string;
        telephone: string;
    }>({
        startTime: "",
        endTime: "",
        callCost: "",
        discount: "",
        totalCost: "",
        netCost: "",
        grossCost: "",
        taxAmount: "",
        telephone: "",
    });

    const [errors, setErrors] = useState<CallFormErrors>({
        startTime: "",
        endTime: "",
        callCost: "",
        discount: "",
        totalCost: "",
        netCost: "",
        grossCost: "",
        taxAmount: "",
    });



    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [discount, setDiscount] = useState('');
    const [telephone, setTelephone] = useState('');
    const [taxAmount, setTaxAmount] = useState<string>('');
    const [callCost, setCallCost] = useState<string>('');
    const [totalTime, setTotalTime] = useState<number>(0);
    const [duration, setDuration] = useState<number>(0);
    const [selectedTelephoneNumber, setSelectedTelephoneNumber] = useState("");
    const [selectedCallIds, setSelectedCallIds] = useState<string[]>([]);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [calls, setCalls] = useState<CallType[]>([]);
    const [totalBill, setTotalBill] = useState<number>(0);
    const [invoiceId, setInvoiceId] = useState("");

    const currentUser = localStorage.getItem("userId");
    const token = localStorage.getItem("token");
    const { isLoggedIn } = useContext(AuthContext);


    const ratePerSecond = 0.001;

    const taxRate = 0.2;

    const handleStartTimeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setStartTime(e.target.value);
    };

    const handleEndTimeChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEndTime(e.target.value);
    };

    const handleDiscountChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setDiscount(e.target.value);
    };

    const handleTelephoneNumberInputChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const name = e.target.name;
        const value = e.target.value;
        setSelectedTelephoneNumber(value);
        setNewCall({ ...newCall, [name]: value });
    };

    const parseTime = (value: string): string => {
        // Your logic to parse time goes here
        return value; // For now, just return the input value as it is
    };

    const calculateInvoice = (): string => {
        createInvoice(totalBill, calls)
            .then((invoiceId) => {
                // Handle success
                console.log("Invoice created with ID: ", invoiceId);
            })
            .catch((error) => {
                // Handle error
                console.error("Error creating invoice: ", error);
            });
        return invoiceId; // Placeholder return value
    };

    const handleCallInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const name = e.target.name;
        let value = e.target.value;
        if (name === "startTime" || name === "endTime" || name === "discount") {
            // Parse time if the input is for start time, end time, or discount
            if (!isNaN(parseFloat(value))) {
                // Assuming parseTime is a function to parse time
                value = parseTime(value);
            } else {
                value = ""; // Clear the value if it's not a valid number
            }
        }
        if (name === "telephone") {
            setTelephone(value);
        }
        setNewCall({ ...newCall, [name]: value });
    };

    // Function to calculate total cost
    const calculateTotalCost = (startTime: string, endTime: string, discount: string): number => {
        // Perform calculations here based on start time, end time, and discount
        // For now, let's assume a simple calculation
        const durationInHours = calculateDurationInHours(startTime, endTime);
        console.log('Duration: '+  durationInHours);
        const callCost = calculateCallCost(durationInHours); // Assuming you have a function to calculate call cost
        const discountedCost = calculateDiscountedCost(callCost, discount); // Assuming you have a function to calculate discounted cost
        console.log('Duration: ', discountedCost);
        return discountedCost;


    };

    const calculateCallCost = (durationInHours: number): number => {
        // Perform calculation for call cost based on the duration
        // For example:
        const ratePerHour = 10; // Rate per hour
        const callCost = durationInHours * ratePerHour; // Simple calculation for demonstration
        console.log('callCost: ', callCost);
        return callCost;
    };

    const calculateDurationInHours = (startTime: string, endTime: string): number => {
        // Parse start time and end time into Date objects
        const startDate = new Date(startTime);
        const endDate = new Date(endTime);

        // Calculate the difference in milliseconds between the two dates
        const durationInMilliseconds = endDate.getTime() - startDate.getTime();

        // Convert the duration from milliseconds to hours
        const durationInHours = durationInMilliseconds / (1000 * 60 * 60);
        console.log('durationInHours: ', durationInHours);
        setDuration(durationInHours);
        return durationInHours;
    };

    // Function to calculate net cost
    const calculateNetCost = (totalCost: number, taxAmount: string): number => {
        // Perform calculations here based on total cost and tax amount
        // For now, let's assume a simple calculation
        const tax = parseFloat(taxAmount);
        console.log('tax: ', totalCost);
        return totalCost + (totalCost * tax) / 100;
    };

    // Function to calculate gross cost
    const calculateGrossCost = (netCost: number, callCost: string): number => {
        // Perform calculations here based on net cost and call cost
        // For now, let's assume a simple calculation
        const callCostValue = parseFloat(callCost);
        console.log('netCost: ', netCost);
        return netCost + callCostValue;
    };

    // Function to calculate call cost after applying discount
    const calculateDiscountedCost = (callCost: number, discount: string): number => {
        // Perform calculations here based on call cost and discount
        // For now, let's assume a simple calculation
        const discountValue = parseFloat(discount);
        console.log('callCost: ', callCost);
        return callCost - (callCost * discountValue) / 100;
    };

    function validateForm() {
        let valid = true;
        const errorsCopy = { ...errors };

        // Regular expression pattern to match HH:mm:ss format
        const timePattern = /^(?:2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]$/;

        // Check if start time is not empty and matches the time format
        if (startTime.trim()) {
            if (!timePattern.test(startTime.trim())) {
                errorsCopy.startTime = "Invalid start time format";
                valid = false;
            } else {
                errorsCopy.startTime = "";
            }
        } else {
            errorsCopy.startTime = "Start time required";
            valid = false;
        }

        // Check if end time is not empty and matches the time format
        if (endTime.trim()) {
            if (!timePattern.test(endTime.trim())) {
                errorsCopy.endTime = "Invalid end time format";
                valid = false;
            } else {
                errorsCopy.endTime = "";
            }
        } else {
            errorsCopy.endTime = "End time required";
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    };

    useEffect(() => {
        // Define an asynchronous function to fetch calls
        const fetchCalls = async () => {
            try {
                // Make an API request to get calls associated with the user ID and token
                const response = await getCallsByUsername(currentUser ?? '');
                // Update the state with the fetched calls
                setCalls(response);
                console.log("get calls ", calls);
            } catch (error) {
                // Handle any errors that occur during the API request
                console.error("Error fetching calls: ", error.message);
                setErrorMessage(error.message);
            }
        };

        // Call the fetchCalls function when the component mounts or when userId or token changes
        fetchCalls();
    }, [currentUser]);

    const navigate = useNavigate();

    const handleProceedToPayment = () => {
        const handleProceedToPayment = () => {
            navigate(`/payment?userId=${currentUser}&netCost=${totalBill}&invoiceId=${invoiceId}`);
        };
    };


    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        // Check if user is logged in
        if (!isLoggedIn()) {
            // Handle case where user is not logged in
            return;
        }

        // Validate the form inputs
        if (!validateForm()) {
            // Handle case where form validation fails
            return;
        }

        // Calculate total cost based on start time, end time, and discount
        const totalCost = calculateTotalCost(startTime, endTime, discount);
        console.log("totalCost ", totalCost)
        // Calculate net cost based on total cost and tax amount
        const netCost = calculateNetCost(totalCost, taxAmount);
        setTotalBill(netCost);
        console.log("totalCost ", netCost)
        // Calculate gross cost based on net cost and call cost
        const grossCost = calculateGrossCost(netCost, callCost);
        console.log("grossCost ", grossCost)
        // Prepare the call object
        const call = {
            startTime: startTime,
            endTime: endTime,
            duration: duration, // Assuming totalTime is calculated elsewhere
            costPerMinute: ratePerSecond * 60, // Assuming cost is per minute
            discountForCalls: parseFloat(discount),
            vat: taxRate,
            netCost: (netCost),
            grossCost: (grossCost),
            totalCost: (totalCost),
            username: currentUser,
            telephone: selectedTelephoneNumber,
            callDate: moment().format("DD/MM/YYYY"),
        };

        try {
            // Check if the phone number exists
            const isValid = await checkPhoneNumberExists(currentUser ?? '', selectedTelephoneNumber);
            if (!isValid) {
                // Handle case where phone number is not valid
                return;
            }

            // Make the call
            const response = await enterCall(call);

            // Check if the call was successfully made
            if (response && response.data && response.data.callId) {
                // Update state and display success message
                setSelectedCallIds([...selectedCallIds, response.data.callId]);
                setSuccessMessage("A new call has been recorded in the database.");
                setNewCall({
                    startTime: "",
                    endTime: "",
                    callCost: "",
                    discount: "",
                    totalCost: "",
                    netCost: "",
                    grossCost: "",
                    taxAmount: "",
                    telephone: "",
                });
                setStartTime("");
                setEndTime("");
                setDiscount("");
                setErrorMessage("");
            } else {
                // Handle case where call was not successfully made
                setErrorMessage("Error adding call to the database");
            }
        } catch (error) {
            // Handle any errors that occur during the process
            setErrorMessage(error.message);
        }

        // Clear success/error messages after a delay
        setTimeout(() => {
            setSuccessMessage("");
            setErrorMessage("");
        }, 3000);
    };


    return (
        <><div className="container">
            <br /> <br />
            <div className="card col-md-6 offset-md-3 offset-md-3">
                <h2 className="text-center">New call</h2>
                <div className="card-body">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group mb-2">
                            <label className="form-label">Call Receiver phone number</label>
                            <div>
                                <CallReceiverSelector
                                    // handleTelephoneNumberInputChange={handleCallInputChange}
                                    handleTelephoneNumberInputChange={handleTelephoneNumberInputChange} // Corrected function here
                                    newCall={newCall} />
                            </div>
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Start Time</label>
                            <input
                                type="text"
                                placeholder="Enter start time (HH:mm:ss)"
                                name="startTime"
                                value={startTime}
                                className={`form-control ${errors.startTime ? "is-invalid" : ""}`}
                                onChange={handleStartTimeChange} />
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">End Time</label>
                            <input
                                type="text"
                                placeholder="Enter end time (HH:mm:ss)"
                                name="endTime"
                                value={endTime}
                                className={`form-control ${errors.endTime ? "is-invalid" : ""}`}
                                onChange={handleEndTimeChange} />
                        </div>
                        <div className="form-group mb-2">
                            <label className="form-label">Discount (%)</label>
                            <input
                                type="text"
                                placeholder="Enter discount"
                                name="discount"
                                value={discount}
                                className="form-control"
                                onChange={handleDiscountChange} />
                        </div>
                        <button className="btn btn-success" type="submit">
                            Submit
                        </button>
                    </form>
                </div>
            </div>

        </div>
            <div className="card-body">
                {calls.length === 0 ? (
                    <p>No calls to display.</p>
                ) : (
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Call ID</th>
                                <th>Start Time</th>
                                <th>End Time</th>
                                <th>Duration</th>
                                <th>Cost Per Minute</th>
                                <th>Discount</th>
                                <th>Call Gross cost</th>
                                <th>VAT</th>
                                <th>Net Cost</th>
                                {/* Add more table headers if needed */}
                            </tr>
                        </thead>
                        <tbody>
                            {/* Map through the calls array and render a table row for each call */}
                            {calls.map((call) => (
                                <tr key={call.id}>
                                    <td>{call.callId}</td>
                                    <td>{call.startTime}</td>
                                    <td>{call.endTime}</td>
                                    <td>{call.duration}</td>
                                    <td>{call.costPerMinute}</td>
                                    <td>{call.discountForCalls}</td>
                                    <td>{call.grossCost}</td>
                                    <td>{call.vat}</td>
                                    <td>{call.netCost}</td>
                                    {/* Add more table cells based on call properties */}
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
                <div>Total Bill Amount: £{totalBill}</div>
                <button className="btn btn-success" >
                    Calculate Bill
                </button>
                <div>
                    <br></br>
                </div>
                <div>
                    <Link
                        to={{
                            pathname: "/payment",
                            state: {
                                userId: currentUser,
                                netCost: totalBill,
                                invoiceId: invoiceId
                            }
                        } as any} // Use type assertion as a temporary workaround
                        className="mb-2 md-mb-0"
                    >
                        Proceed To Payment
                    </Link>
                </div>
                <div>
                    {/* Your component content */}
                    <button
                        className="btn btn-success"
                        onClick={handleProceedToPayment}
                    >
                        Proceed to Payment
                    </button>
                </div>
            </div></>
    )

};

export default Call;