package opticaltelephonecompany.otc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opticaltelephonecompany.otc.models.Call;
import opticaltelephonecompany.otc.models.CallReceiver;
import opticaltelephonecompany.otc.models.Users;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
  Optional<Call> findByCallId(Long callsId);
  
  //List<Call> findAllByReceiverUsername(String username);

  //List<Call> findAllCallReceiverByUserName(String username);

  //List<Call> receiver();
	
    //List<Call> findByCallUserUsername(String username);
  
    //List<CallReceiver> findByCallUsersUsername(String username);

    //List<Call> findByReceiverUsername(String username);
}
