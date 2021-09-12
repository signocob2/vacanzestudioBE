package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneVacanzaFamigliaRepository extends JpaRepository<PrenotazioneVacanzaFamiglia, Long> {

    List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByUtenteId(Long id);

    Optional<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByVacanzaIdAndEmailAmico(Long id, String emailAmico);
}
