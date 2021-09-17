package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.CertificatoDao;
import univr.ingegneria.vacanzestudio.model.Certificato;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CertificatoService {
    @Resource
    CertificatoDao certificatoDao;

    public Certificato findCertificatoById(Long idCertificato) {
        return certificatoDao.findById(idCertificato).orElse(null);
    }

    public List<Certificato> findAllCertificatoByUtenteId(Long idUtente) {
        return certificatoDao.findCertificatoByUtenteId(idUtente);
    }

    public Certificato findCertificatoByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return certificatoDao.findCertificatoByUtenteIdAndVacanzaId(idUtente, idVacanza).orElse(null);
    }
}
