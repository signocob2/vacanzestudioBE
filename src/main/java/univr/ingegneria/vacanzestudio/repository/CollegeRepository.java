package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.College;

public interface CollegeRepository extends JpaRepository<College, Long> {
}
