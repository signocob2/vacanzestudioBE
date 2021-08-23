package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Allergia;

import java.util.Optional;

public interface AllergiaRepository extends JpaRepository<Allergia, Long> {
    void deleteAllergiaById(Long id);

    Optional<Allergia> findAllergiaById(Long id);
}
