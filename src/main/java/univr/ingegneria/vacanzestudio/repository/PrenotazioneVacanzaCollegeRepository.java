package univr.ingegneria.vacanzestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;

import java.util.List;

public interface PrenotazioneVacanzaCollegeRepository extends JpaRepository<PrenotazioneVacanzaCollege, Long> {

    List<PrenotazioneVacanzaCollege> findPrenotazioneVacanzaCollegeByUtenteId(Long id);
}
