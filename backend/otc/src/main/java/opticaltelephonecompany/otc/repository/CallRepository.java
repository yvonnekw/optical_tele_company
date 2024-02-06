package opticaltelephonecompany.otc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.CallUser;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
	Optional<Call> findByCallId(Long callsId);
	
    //List<Call> findByCallUserUsername(String username);
  
    List<CallReceiver> findByCallUsersUsername(String username);
}
