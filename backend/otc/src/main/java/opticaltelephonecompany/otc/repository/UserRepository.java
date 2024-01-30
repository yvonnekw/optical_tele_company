package opticaltelephonecompany.otc.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import opticaltelephonecompany.otc.models.CallerUser;

@Repository
public interface UserRepository extends JpaRepository<CallerUser, Long> {
	Optional<CallerUser> findByUsername(String username);
}
