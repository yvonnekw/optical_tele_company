package com.otcompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.otcompany.models.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {
	Optional<Call> findByCallId(Long callsId);
  
}
