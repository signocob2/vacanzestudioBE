package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Studente;

public interface StudenteRepository extends JpaRepository<Studente, Long> {
    Studente findStudenteById(Long id);

    Studente findStudenteByEmailAndPassword(String email, String password);
}
