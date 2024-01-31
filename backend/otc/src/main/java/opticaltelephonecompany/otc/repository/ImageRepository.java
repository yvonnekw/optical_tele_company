package opticaltelephonecompany.otc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import opticaltelephonecompany.otc.models.Image;
import java.util.List;
import java.util.Optional;


public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByImageName(String imageName);

}
