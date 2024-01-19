package com.otc.opticaltc.repositories;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.otc.opticaltc.models.Call;

//@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
	
  
}
