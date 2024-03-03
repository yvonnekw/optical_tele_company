import React, { useState, useEffect } from 'react';
import { getAllInvoices } from '../../services/InvoiceService';

const Invoice = () => {
  const [invoices, setInvoices] = useState([]);

  useEffect(() => {
    const fetchInvoices = async () => {
      try {
        const invoicesData = await getAllInvoices(); // Call the function to fetch invoices
        setInvoices(invoicesData); // Update state with fetched invoices
      } catch (error) {
        console.error('Error fetching invoices:', error);
      }
    };

    fetchInvoices();
  }, []); // Empty dependency array ensures the effect runs only once

return (
  <div>
    <h2>Invoices</h2>
    {invoices.length === 0 ? (
      <p>No invoices to display.</p>
    ) : (
      <table>
        <thead>
          <tr>
            <th>Invoice ID</th>
            <th>Call IDs</th>
            <th>Amount</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {invoices.map((invoice) => (
            <tr key={invoice.invoiceId}>
              <td>{invoice.invoiceId}</td>
              <td>
                {invoice.callIds.length === 0 ? (
                  "N/A"
                ) : (
                  invoice.callIds.join(", ")
                )}
              </td>
              <td>{invoice.amount}</td>
              <td>{invoice.invoiceDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    )}
  </div>
);
  
};

export default Invoice;