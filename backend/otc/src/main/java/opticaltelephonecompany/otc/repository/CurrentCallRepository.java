package opticaltelephonecompany.otc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import opticaltelephonecompany.otc.models.CurrentCall;

public interface CurrentCallRepository extends JpaRepository<CurrentCall, Long> {

}

