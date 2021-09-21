package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Utente;

import java.util.Optional;

public interface UtenteDao extends JpaRepository<Utente, Long> {
    Optional<Utente> findUtenteById(Long id);

    Optional<Utente> findUtenteByEmail(String email);

    Optional<Utente> findUtenteByCodiceFiscale(String codiceFiscale);

    Utente findUtenteByEmailAndPassword(String email, String password);
}
