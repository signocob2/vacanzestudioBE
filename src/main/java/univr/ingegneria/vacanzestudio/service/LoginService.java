package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.UtenteDao;
import univr.ingegneria.vacanzestudio.model.Utente;

import javax.annotation.Resource;

@Service
public class LoginService {
    @Resource
    private UtenteDao utenteDao;

    public Utente isAuthenticatedByEmailAndPassword(String email, String password) {
        return utenteDao.findUtenteByEmailAndPassword(email, password);
    }
}
