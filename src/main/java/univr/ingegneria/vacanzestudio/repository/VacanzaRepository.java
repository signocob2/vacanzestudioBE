package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import java.util.Optional;

public interface VacanzaRepository extends JpaRepository<Vacanza, Long> {
    Optional<Vacanza> findVacanzaById(Long id);
}
