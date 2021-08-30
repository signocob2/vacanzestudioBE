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
        studente.setId(0L);
        studente.setUtente_inserimento(studente.getEmail());
        studente.setUtente_modifica(studente.getEmail());
        return studenteRepository.save(studente);
    }
}
