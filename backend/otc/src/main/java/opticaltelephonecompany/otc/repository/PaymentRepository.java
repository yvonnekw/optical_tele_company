package opticaltelephonecompany.otc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opticaltelephonecompany.otc.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    

    
}
