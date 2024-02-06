package opticaltelephonecompany.otc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CallUser;

@Repository
public interface CallReceiverRepository extends JpaRepository<CallReceiver, Long> {
	Optional<CallReceiver> findByCallReceiverId(Long callReceiverId);
	
	//List<CallReceiver> findByCallUser(CallUser callUser);
	
	Optional<CallReceiver> findByTelephone(String telephone);

	List<CallReceiver> findByCallUser_username(String username);
  
}
