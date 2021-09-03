package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.AllergiaNotFoundException;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.repository.AllergiaRepository;
import univr.ingegneria.vacanzestudio.repository.StudenteRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudenteService {
    @Resource
    StudenteRepository studenteRepository;

    public Studente addStudente(Studente studente) {
        System.out.println("************");
        System.out.println("StudenteService.addStudente");
        studente.setUtente_inserimento(studente.getEmail());
        studente.setUtente_modifica(studente.getEmail());

        // Allergie
        studente.getAllergiaList().forEach(allergia -> {
            allergia.setStudente(studente);
            allergia.setUtente_inserimento(studente.getUtente_inserimento());
            allergia.setUtente_modifica(studente.getUtente_modifica());
        });

        System.out.println("StudenteService fine allergie");

        // Hobby
        studente.getHobbyList().forEach(hobby -> {
            hobby.setStudente(studente);
            hobby.setUtente_inserimento(studente.getUtente_inserimento());
            hobby.setUtente_modifica(studente.getUtente_modifica());
        });

        System.out.println("StudenteService fine hobby");
        // Genitori
        studente.getGenitoreList().forEach(genitore -> {
            genitore.setStudente(studente);
            genitore.setUtente_inserimento(studente.getUtente_inserimento());
            genitore.setUtente_modifica(studente.getUtente_modifica());
        });

        System.out.println("StudenteService fine genitori");
        return studenteRepository.save(studente);
    }
}
