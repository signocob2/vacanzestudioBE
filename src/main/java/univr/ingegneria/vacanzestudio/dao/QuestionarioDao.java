package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.Questionario;

import java.util.Optional;

public interface QuestionarioDao extends JpaRepository<Questionario, Long> {
    Optional<Questionario> findQuestionarioByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza);
}
