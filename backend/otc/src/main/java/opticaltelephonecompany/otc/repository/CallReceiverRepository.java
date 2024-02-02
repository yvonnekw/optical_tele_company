package opticaltelephonecompany.otc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import opticaltelephonecompany.otc.models.CallReceiver;

@Repository
public interface CallReceiverRepository extends JpaRepository<CallReceiver, Long> {
	Optional<CallReceiver> findByCallReceiverId(Long callReceiverId);
  
}
