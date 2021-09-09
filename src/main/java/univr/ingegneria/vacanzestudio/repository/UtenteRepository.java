package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Utente;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findUtenteById(Long id);

    Optional<Utente> findUtenteByEmail(String email);

    Utente findUtenteByEmailAndPassword(String email, String password);
}
