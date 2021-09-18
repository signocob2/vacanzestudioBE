package univr.ingegneria.vacanzestudio.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.CertificatoDao;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.model.Utente;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SimulaAvanzamentoService {
    @Resource
    CertificatoService certificatoService;

    @Resource
    VacanzaService vacanzaService;

    @Resource
    UtenteService utenteService;

    @Resource
    private CertificatoDao certificatoDao;

    public void addCertificato(Certificato certificato) {
        certificatoDao.save(certificato);
    }

    public void gestioneCertificati(LocalDate dataCorrenteSimulata) {
        List<Vacanza> vacanzaList = vacanzaService.findAllVacanza();
        List<Utente> utenteList = new ArrayList<>(utenteService.findAllUtente());

        List<Vacanza> vacanzaTerminataList = new ArrayList<>();
        vacanzaList.forEach(v -> {
            LocalDate dataFineVacanza = v.getDataDiPartenza().plusWeeks(v.getNumeroDiSettimaneDurata());
            if (dataCorrenteSimulata.isAfter(dataFineVacanza) || dataCorrenteSimulata.isEqual(dataFineVacanza)) {
                vacanzaTerminataList.add(v);
            }
        });

        vacanzaTerminataList.forEach(v -> {
            List<Utente> studentiCheHannoPartecipatoAllaVacanza = new ArrayList<>();

            utenteList.forEach(u -> {
                u.getPrenotazioneVacanzaCollegeList().forEach(p -> {
                    if (p.getVacanza().getId().equals(v.getId())) {
                        studentiCheHannoPartecipatoAllaVacanza.add(u);
                    }
                });
                u.getPrenotazioneVacanzaFamigliaList().forEach(p -> {
                    if (p.getVacanza().getId().equals(v.getId())) {
                        studentiCheHannoPartecipatoAllaVacanza.add(u);
                    }
                });
            });

            studentiCheHannoPartecipatoAllaVacanza.forEach(s -> {
                if (certificatoService.findCertificatoByUtenteIdAndVacanzaId(s.getId(), v.getId()) == null) {
                    inserisciCertificato(s.getId(), v.getId());
                }
            });

        });
    }

    private void inserisciCertificato(Long idUtente, Long idVacanza) {
        Certificato certificato = new Certificato();

        Utente utente = new Utente();
        utente.setId(idUtente);

        Vacanza vacanza = new Vacanza();
        vacanza.setId(idVacanza);

        certificato.setUtente(utente);
        certificato.setVacanza(vacanza);

        String randomLivelloRaggiunto = StringUtils.EMPTY;
        Random r = new Random();
        int randomForChar = r.nextInt(3) + 1;
        int randomForInt = r.nextInt(2) + 1;
        switch (randomForChar) {
            case 1:
                randomLivelloRaggiunto += "A";
                break;
            case 2:
                randomLivelloRaggiunto += "B";
                break;
            default:
                randomLivelloRaggiunto += "C";
                break;
        }

        if (randomForInt == 1) {
            randomLivelloRaggiunto += "1";
        } else {
            randomLivelloRaggiunto += "2";
        }

        certificato.setLivelloRaggiunto(randomLivelloRaggiunto);
        this.addCertificato(certificato);
    }
}
