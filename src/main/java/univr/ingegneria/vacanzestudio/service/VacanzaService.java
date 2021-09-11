package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.VacanzaException;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.repository.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class VacanzaService {
    @Resource
    private VacanzaRepository vacanzaRepository;

    @Resource
    private FamigliaRepository famigliaRepository;

    @Resource
    private CollegeRepository collegeRepository;

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

    public Vacanza addVacanza(Vacanza vacanza) {
        return prepareAndSaveVacanza(vacanza);
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


    private Vacanza prepareAndSaveVacanza(Vacanza vacanza) {
        if (Objects.isNull(vacanza.getFamiglia().getId())) {
            vacanza.getFamiglia().setId(0L);
            vacanza.setFamiglia(famigliaRepository.save(vacanza.getFamiglia()));
        }

        if (Objects.isNull(vacanza.getCollege().getId())) {
            vacanza.getCollege().setId(0L);
            vacanza.getCollege().getAttivitaCollegeList().forEach(attivitaCollege -> {
                attivitaCollege.setCollege(vacanza.getCollege());
                attivitaCollege.setUtente_inserimento(vacanza.getUtente_inserimento());
                attivitaCollege.setUtente_modifica(vacanza.getUtente_modifica());
            });
            vacanza.setCollege(collegeRepository.save(vacanza.getCollege()));
        }

        vacanza.setUtente_inserimento(vacanza.getUtente_inserimento());
        vacanza.setUtente_modifica(vacanza.getUtente_inserimento());

        // Gite
        vacanza.getGitaList().forEach(gita -> {
            gita.setVacanza(vacanza);
            gita.setUtente_inserimento(vacanza.getUtente_inserimento());
            gita.setUtente_modifica(vacanza.getUtente_modifica());
        });

        return vacanzaRepository.save(vacanza);
    }
}
