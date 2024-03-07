package opticaltelephonecompany.otc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import opticaltelephonecompany.otc.models.Payment;
import opticaltelephonecompany.otc.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
    
      private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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
        // You may want to add additional logic here before saving the payment
        return paymentRepository.save(payment);
    }

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
}

