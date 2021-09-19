package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneVacanzaFamigliaDao extends JpaRepository<PrenotazioneVacanzaFamiglia, Long> {

    List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByUtenteId(Long id);

    Optional<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByVacanzaIdAndUtenteEmail(Long id, String emailAmico);
}
