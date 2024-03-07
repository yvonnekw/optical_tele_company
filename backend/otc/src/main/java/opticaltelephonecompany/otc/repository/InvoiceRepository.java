package opticaltelephonecompany.otc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opticaltelephonecompany.otc.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    
}
