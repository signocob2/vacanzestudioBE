package univr.ingegneria.vacanzestudio.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.SimulaAvanzamentoDto;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.model.Utente;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.CertificatoService;
import univr.ingegneria.vacanzestudio.service.SimulaAvanzamentoService;
import univr.ingegneria.vacanzestudio.service.UtenteService;
import univr.ingegneria.vacanzestudio.service.VacanzaService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/simulaAvanzamento")
class SimulaAvanzamentoController {
    @Resource
    SimulaAvanzamentoService simulaAvanzamentoService;

    @Resource
    VacanzaService vacanzaService;

    @Resource
    UtenteService utenteService;

    @Resource
    CertificatoService certificatoService;

    @PostMapping("/simula")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void simula(@RequestBody SimulaAvanzamentoDto simulaAvanzamentoDto) {
        gestioneCertificati(simulaAvanzamentoDto);
    }

    private void gestioneCertificati(SimulaAvanzamentoDto simulaAvanzamentoDto) {
        List<Vacanza> vacanzaList = vacanzaService.findAllVacanza();
        List<Utente> utenteList = new ArrayList<>(utenteService.findAllUtente());

        List<Vacanza> vacanzaTerminataList = new ArrayList<>();
        vacanzaList.forEach(v -> {
            LocalDate dataFineVacanza = v.getDataDiPartenza().plusWeeks(v.getNumeroDiSettimaneDurata());
            LocalDate dataCorrenteSimulata = simulaAvanzamentoDto.getDataCorrenteSimulata();
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
        simulaAvanzamentoService.addCertificato(certificato);
    }
}