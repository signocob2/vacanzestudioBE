package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import java.util.Optional;

public interface VacanzaDao extends JpaRepository<Vacanza, Long> {
    Optional<Vacanza> findVacanzaById(Long id);
}
