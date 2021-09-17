package univr.ingegneria.vacanzestudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;

import java.util.List;

public interface PrenotazioneVacanzaCollegeDao extends JpaRepository<PrenotazioneVacanzaCollege, Long> {

    List<PrenotazioneVacanzaCollege> findPrenotazioneVacanzaCollegeByUtenteId(Long id);
}
