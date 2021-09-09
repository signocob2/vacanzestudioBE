package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.VacanzaException;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.repository.PrenotazioneVacanzaCollegeRepository;
import univr.ingegneria.vacanzestudio.repository.PrenotazioneVacanzaFamigliaRepository;
import univr.ingegneria.vacanzestudio.repository.VacanzaRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VacanzaService {
    @Resource
    private VacanzaRepository vacanzaRepository;

    @Resource
    private PrenotazioneVacanzaCollegeRepository prenotazioneVacanzaCollegeRepository;

    @Resource
    private PrenotazioneVacanzaFamigliaRepository prenotazioneVacanzaFamigliaRepository;

    public List<Vacanza> findAllVacanza() {
        return vacanzaRepository.findAll();
    }

    public Vacanza findVacanzaById(Long id) {
        return vacanzaRepository.findVacanzaById(id)
                .orElseThrow(() -> new VacanzaException("Vacanza con id" + id + " non trovata"));
    }

    public PrenotazioneVacanzaCollege addVacanzaCollege(PrenotazioneVacanzaCollege prenotazioneVacanzaCollege) {
        return prenotazioneVacanzaCollegeRepository.save(prenotazioneVacanzaCollege);
    }

    public PrenotazioneVacanzaFamiglia addVacanzaFamiglia(PrenotazioneVacanzaFamiglia prenotazioneVacanzaFamiglia) {
        return prenotazioneVacanzaFamigliaRepository.save(prenotazioneVacanzaFamiglia);
    }

    public List<PrenotazioneVacanzaCollege> findPrenotazioneVacanzaCollegeByIdUtente(Long id) {
        return prenotazioneVacanzaCollegeRepository.findPrenotazioneVacanzaCollegeByUtenteId(id);
    }

    public List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByIdUtente(Long id) {
        return prenotazioneVacanzaFamigliaRepository.findPrenotazioneVacanzaFamigliaByUtenteId(id);
    }
}
