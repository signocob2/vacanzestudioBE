package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.College;

public interface CollegeDao extends JpaRepository<College, Long> {
}
