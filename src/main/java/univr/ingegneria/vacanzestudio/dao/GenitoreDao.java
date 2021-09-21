package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Genitore;

import java.util.Optional;

public interface GenitoreDao extends JpaRepository<Genitore, Long> {
    Optional<Genitore> findGenitoreByEmail(String email);
}
