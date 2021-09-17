package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Famiglia;

public interface FamigliaDao extends JpaRepository<Famiglia, Long> {
}
