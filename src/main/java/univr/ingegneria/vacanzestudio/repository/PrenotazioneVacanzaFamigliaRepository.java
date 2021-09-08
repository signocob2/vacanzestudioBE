package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;

import java.util.List;

public interface PrenotazioneVacanzaFamigliaRepository extends JpaRepository<PrenotazioneVacanzaFamiglia, Long> {

    List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByStudenteId(Long id);
}
