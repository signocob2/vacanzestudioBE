package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.repository.CertificatoRepository;

import javax.annotation.Resource;

@Service
public class CertificatoService {
    @Resource
    CertificatoRepository certificatoRepository;

    public Certificato findCertificatoByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return certificatoRepository.findCertificatoByUtenteIdAndVacanzaId(idUtente, idVacanza).orElse(null);
    }
}
