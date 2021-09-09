package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Utente;
import univr.ingegneria.vacanzestudio.repository.UtenteRepository;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    private UtenteRepository utenteRepository;

    public Utente isAuthenticatedByEmailAndPassword(String email, String password) {
        return utenteRepository.findUtenteByEmailAndPassword(email, password);
    }
}
