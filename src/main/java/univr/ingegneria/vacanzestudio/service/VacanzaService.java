package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.*;
import univr.ingegneria.vacanzestudio.exception.VacanzaException;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class VacanzaService {
    @Resource
    private VacanzaDao vacanzaDao;

    @Resource
    private FamigliaDao famigliaDao;

    @Resource
    private CollegeDao collegeDao;

    @Resource
    private PrenotazioneVacanzaCollegeDao prenotazioneVacanzaCollegeDao;

    @Resource
    private PrenotazioneVacanzaFamigliaDao prenotazioneVacanzaFamigliaDao;

    public List<Vacanza> findAllVacanza() {
        return vacanzaDao.findAll();
    }

    public Vacanza findVacanzaById(Long id) {
        return vacanzaDao.findVacanzaById(id)
                .orElseThrow(() -> new VacanzaException("Vacanza con id" + id + " non trovata"));
    }

    public PrenotazioneVacanzaFamiglia findPrenotazioneVacanzaFamigliaByVacanzaIdAndEmailAmico(Long id, String emailAmico) {
        return prenotazioneVacanzaFamigliaDao.findPrenotazioneVacanzaFamigliaByVacanzaIdAndEmailAmico(id, emailAmico).orElse(null);
    }

    public Vacanza addVacanza(Vacanza vacanza) {
        return prepareAndSaveVacanza(vacanza);
    }

    public PrenotazioneVacanzaCollege addVacanzaCollege(PrenotazioneVacanzaCollege prenotazioneVacanzaCollege) {
        return prenotazioneVacanzaCollegeDao.save(prenotazioneVacanzaCollege);
    }

    public PrenotazioneVacanzaFamiglia addVacanzaFamiglia(PrenotazioneVacanzaFamiglia prenotazioneVacanzaFamiglia) {
        return prenotazioneVacanzaFamigliaDao.save(prenotazioneVacanzaFamiglia);
    }

    public List<PrenotazioneVacanzaCollege> findPrenotazioneVacanzaCollegeByIdUtente(Long id) {
        return prenotazioneVacanzaCollegeDao.findPrenotazioneVacanzaCollegeByUtenteId(id);
    }

    public List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByIdUtente(Long id) {
        return prenotazioneVacanzaFamigliaDao.findPrenotazioneVacanzaFamigliaByUtenteId(id);
    }


    private Vacanza prepareAndSaveVacanza(Vacanza vacanza) {
        if (Objects.isNull(vacanza.getFamiglia().getId())) {
            vacanza.getFamiglia().setId(0L);
            vacanza.setFamiglia(famigliaDao.save(vacanza.getFamiglia()));
        }

        if (Objects.isNull(vacanza.getCollege().getId())) {
            vacanza.getCollege().setId(0L);
            vacanza.getCollege().getAttivitaCollegeList().forEach(attivitaCollege -> {
                attivitaCollege.setCollege(vacanza.getCollege());
                attivitaCollege.setUtente_inserimento(vacanza.getUtente_inserimento());
                attivitaCollege.setUtente_modifica(vacanza.getUtente_modifica());
            });
            vacanza.setCollege(collegeDao.save(vacanza.getCollege()));
        }

        vacanza.setUtente_inserimento(vacanza.getUtente_inserimento());
        vacanza.setUtente_modifica(vacanza.getUtente_inserimento());

        // Gite
        vacanza.getGitaList().forEach(gita -> {
            gita.setVacanza(vacanza);
            gita.setUtente_inserimento(vacanza.getUtente_inserimento());
            gita.setUtente_modifica(vacanza.getUtente_modifica());
        });

        return vacanzaDao.save(vacanza);
    }
}
