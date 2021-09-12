package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.repository.CertificatoRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CertificatoService {
    @Resource
    CertificatoRepository certificatoRepository;

    public Certificato findCertificatoById(Long idCertificato) {
        return certificatoRepository.findById(idCertificato).orElse(null);
    }

    public List<Certificato> findAllCertificatoByUtenteId(Long idUtente) {
        return certificatoRepository.findCertificatoByUtenteId(idUtente);
    }

    public Certificato findCertificatoByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return certificatoRepository.findCertificatoByUtenteIdAndVacanzaId(idUtente, idVacanza).orElse(null);
    }
}
