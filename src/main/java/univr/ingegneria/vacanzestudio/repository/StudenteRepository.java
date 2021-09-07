package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Studente;

import java.util.Optional;

public interface StudenteRepository extends JpaRepository<Studente, Long> {
    Studente findStudenteById(Long id);

    Optional<Studente> findStudenteByEmail(String email);

    Studente findStudenteByEmailAndPassword(String email, String password);
}
