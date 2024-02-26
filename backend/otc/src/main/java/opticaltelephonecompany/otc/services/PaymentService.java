package opticaltelephonecompany.otc.services;

import java.util.List;

import opticaltelephonecompany.otc.models.Payment;

public interface PaymentService {

    public List<Payment> getAllPayments(); 

    public Payment getPaymentById(Long paymentId);

    public Payment createPayment(Payment payment);

    public Payment updatePayment(Long paymentId, Payment paymentDetails);

    public void deletePayment(Long paymentId); 
}
