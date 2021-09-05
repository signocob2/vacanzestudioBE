package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.repository.StudenteRepository;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    private StudenteRepository studenteRepository;

    public Studente isAuthenticatedByEmailAndPassword(String email, String password) {
        return studenteRepository.findStudenteByEmailAndPassword(email, password);
    }
}
