package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.repository.StudenteRepository;

import javax.annotation.Resource;

@Service
public class StudenteService {
    @Resource
    StudenteRepository studenteRepository;

    public Studente findStudenteById(Long idStudente) {
        return studenteRepository.findStudenteById(idStudente);
    }

    public Studente addStudente(Studente studente) {
        return prepareAndSaveStudente(studente);
    }

    public Studente updateStudente(Studente studente) {
        return prepareAndSaveStudente(studente);
    }

    private Studente prepareAndSaveStudente(Studente studente) {
        studente.setUtente_inserimento(studente.getEmail());
        studente.setUtente_modifica(studente.getEmail());

        // Allergie
        studente.getAllergiaList().forEach(allergia -> {
            allergia.setStudente(studente);
            allergia.setUtente_inserimento(studente.getUtente_inserimento());
            allergia.setUtente_modifica(studente.getUtente_modifica());
        });

        // Hobby
        studente.getHobbyList().forEach(hobby -> {
            hobby.setStudente(studente);
            hobby.setUtente_inserimento(studente.getUtente_inserimento());
            hobby.setUtente_modifica(studente.getUtente_modifica());
        });

        // Genitori
        studente.getGenitoreList().forEach(genitore -> {
            genitore.setStudente(studente);
            genitore.setUtente_inserimento(studente.getUtente_inserimento());
            genitore.setUtente_modifica(studente.getUtente_modifica());
        });

        return studenteRepository.save(studente);
    }
}
