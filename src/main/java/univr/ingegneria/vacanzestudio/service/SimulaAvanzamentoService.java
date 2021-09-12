package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.repository.CertificatoRepository;

import javax.annotation.Resource;

@Service
public class SimulaAvanzamentoService {
    @Resource
    private CertificatoRepository certificatoRepository;

    public Certificato addCertificato(Certificato certificato) {
        return certificatoRepository.save(certificato);
    }
}
