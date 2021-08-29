package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.AllergiaNotFoundException;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.repository.AllergiaRepository;
import univr.ingegneria.vacanzestudio.repository.StudenteRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginService {
    @Resource
    private StudenteRepository studenteRepository;

    public boolean isAuthenticatedByEmailAndPassword(String email, String password) {
        return studenteRepository.findStudenteByEmailAndPassword(email, password) != null;
    }
}
