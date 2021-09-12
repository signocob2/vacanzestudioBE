package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Certificato;

import java.util.Optional;

public interface CertificatoRepository extends JpaRepository<Certificato, Long> {
    Optional<Certificato> findCertificatoByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza);
}
