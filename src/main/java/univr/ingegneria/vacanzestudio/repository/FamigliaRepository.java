package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Famiglia;

public interface FamigliaRepository extends JpaRepository<Famiglia, Long> {
}
