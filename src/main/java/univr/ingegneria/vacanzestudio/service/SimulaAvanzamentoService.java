package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.CertificatoDao;
import univr.ingegneria.vacanzestudio.model.Certificato;

import javax.annotation.Resource;

@Service
public class SimulaAvanzamentoService {
    @Resource
    private CertificatoDao certificatoDao;

    public Certificato addCertificato(Certificato certificato) {
        return certificatoDao.save(certificato);
    }
}
