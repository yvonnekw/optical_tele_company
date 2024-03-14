package opticaltelephonecompany.otc.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.Invoice;
import opticaltelephonecompany.otc.models.Payment;
import opticaltelephonecompany.otc.repository.CallRepository;
import opticaltelephonecompany.otc.repository.InvoiceRepository;
import opticaltelephonecompany.otc.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

        private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    
    private final PaymentRepository paymentRepository;
    private final CallRepository callRepository;
        private final InvoiceRepository invoiceRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, CallRepository callRepository, InvoiceRepository invoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.callRepository = callRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        return paymentOptional.orElse(null); // Return null if payment is not found
    }

    @Override
    public Payment createPayment(Payment payment) {
        Invoice invoice = payment.getInvoice();
        invoice.setPaid(true); // Set invoice as paid

        // Iterate through the calls and update their isPaid status
        for (Call call : invoice.getCalls()) {
            call.setPaid(true); // Set call as paid
        }

        // Save the invoice and associated calls
        invoiceRepository.save(invoice);

        // Log the updated invoice and associated calls for debugging
        logger.info("Updated Invoice: {}", invoice);
        logger.info("Updated Calls: {}", invoice.getCalls());

        return paymentRepository.save(payment);
    }
/* 
    @Override
    public Payment createPayment(Payment payment) {
        Invoice invoice = payment.getInvoice();
        invoice.setPaid(true);
        invoiceRepository.save(invoice);

        Set<Call> calls = invoice.getCalls();

        // Iterate through the calls and update their isPaid status
        for (Call call : calls) {
            call.setPaid(true);
            callRepository.save(call);
            logger.info("calls being updaed with payment : {}", call);
        }

        return paymentRepository.save(payment);
    }
*/
    /* 
    @Override
    public Payment createPayment(Payment payment) {
        Invoice invoice = payment.getInvoice();
        invoice.setPaid(true);
        invoiceRepository.save(invoice);
        return paymentRepository.save(payment);
    }
*/
    @Override
    public Payment updatePayment(Long paymentId, Payment paymentDetails) {
        // Check if the payment exists
        Payment existingPayment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        // Update the existing payment with the details provided
        existingPayment.setAmount(paymentDetails.getAmount());
        // Update other fields as needed

        // Save and return the updated payment
        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        // Check if the payment exists
        Payment existingPayment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        // Delete the payment
        paymentRepository.delete(existingPayment);
    }

    /* 
    @Override
    public List<Call> getPaidCallsByUsername(String username) {

        return callRepository.findByUser_UsernameAndIsPaidTrue(username);
    }
    */
}

