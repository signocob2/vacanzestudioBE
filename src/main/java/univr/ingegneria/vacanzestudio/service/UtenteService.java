package univr.ingegneria.vacanzestudio.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.GenitoreDao;
import univr.ingegneria.vacanzestudio.dao.UtenteDao;
import univr.ingegneria.vacanzestudio.exception.UtenteException;
import univr.ingegneria.vacanzestudio.model.Genitore;
import univr.ingegneria.vacanzestudio.model.Utente;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UtenteService {
    @Resource
    UtenteDao utenteDao;

    @Resource
    GenitoreDao genitoreDao;

    public List<Utente> findAllUtente() {
        return utenteDao.findAll();
    }

    public Utente findUtenteById(Long idUtente) {
        return utenteDao.findUtenteById(idUtente)
                .orElseThrow(() -> new UtenteException("Modifica non ammessa - Utente con id " + idUtente + " non trovato"));
    }

    public Utente addUtente(Utente utente) {
        checkEmailLibera(utente);
        checkCodiceFiscaleLibero(utente);

        utente.getGenitoreList().forEach(this::checkEmailLibera);

        return prepareAndSaveUtente(utente);
    }

    public Utente updateUtente(Utente newUtente) {
        // Recupero il vecchio utente
        Utente oldUtente = utenteDao.findById(newUtente.getId())
                .orElseThrow(() -> new UtenteException("Modifica non effettuata - Utente con id " + newUtente.getId() + " non trovato"));

        // Controllo se la mail esiste già
        if (!StringUtils.equals(oldUtente.getEmail(), newUtente.getEmail())) {
            checkEmailLibera(newUtente);
        }

        // Controllo se la mail esiste già
        if (!StringUtils.equals(oldUtente.getCodiceFiscale(), newUtente.getCodiceFiscale())) {
            checkCodiceFiscaleLibero(newUtente);
        }

        for (Genitore ng : newUtente.getGenitoreList()) {
            boolean isEmailGiaPresente = false;
            for (Genitore og : oldUtente.getGenitoreList()) {
                if (StringUtils.equals(og.getEmail(), ng.getEmail())) {
                    isEmailGiaPresente = true;
                }
            }

            if (!isEmailGiaPresente) {
                checkEmailLibera(ng);
            }
        }

        newUtente.setId(oldUtente.getId());

        // Non devo perdere le vecchie prenotazioni
        newUtente.setPrenotazioneVacanzaCollegeList(oldUtente.getPrenotazioneVacanzaCollegeList());
        newUtente.getPrenotazioneVacanzaCollegeList().forEach(p -> p.setUtente(newUtente));

        newUtente.setPrenotazioneVacanzaFamigliaList(oldUtente.getPrenotazioneVacanzaFamigliaList());
        newUtente.getPrenotazioneVacanzaFamigliaList().forEach(p -> p.setUtente(newUtente));

        // Non devo perdere i vecchi certificati
        newUtente.setCertificatoList(oldUtente.getCertificatoList());
        newUtente.getCertificatoList().forEach(c -> c.setUtente(newUtente));

        // Non devo perdere i vecchi questionari
        newUtente.setQuestionarioList(oldUtente.getQuestionarioList());
        newUtente.getQuestionarioList().forEach(q -> q.setUtente(newUtente));

        utenteDao.delete(oldUtente);
        return prepareAndSaveUtente(newUtente);
    }

    private void checkEmailLibera(Utente newUtente) {
        utenteDao.findUtenteByEmail(newUtente.getEmail()).ifPresent(s -> {
            throw new UtenteException("Errore - Utente con email " + s.getEmail() + " già presente");
        });
    }

    private void checkEmailLibera(Genitore genitore) {
        genitoreDao.findGenitoreByEmail(genitore.getEmail()).ifPresent(s -> {
            throw new UtenteException("Errore - Genitore con email " + s.getEmail() + " già presente");
        });
    }

    private void checkCodiceFiscaleLibero(Utente newUtente) {
        utenteDao.findUtenteByCodiceFiscale(newUtente.getCodiceFiscale()).ifPresent(s -> {
            throw new UtenteException("Errore - Utente con codice fiscale " + s.getCodiceFiscale() + " già presente");
        });
    }

    private Utente prepareAndSaveUtente(Utente utente) {
        utente.setUtente_inserimento(utente.getEmail());
        utente.setUtente_modifica(utente.getEmail());

        // Allergie
        utente.getAllergiaList().forEach(allergia -> {
            allergia.setUtente(utente);
            allergia.setUtente_inserimento(utente.getUtente_inserimento());
            allergia.setUtente_modifica(utente.getUtente_modifica());
        });

        // Hobby
        utente.getHobbyList().forEach(hobby -> {
            hobby.setUtente(utente);
            hobby.setUtente_inserimento(utente.getUtente_inserimento());
            hobby.setUtente_modifica(utente.getUtente_modifica());
        });

        // Genitori
        utente.getGenitoreList().forEach(genitore -> {
            genitore.setUtente(utente);
            genitore.setUtente_inserimento(utente.getUtente_inserimento());
            genitore.setUtente_modifica(utente.getUtente_modifica());
        });

        return utenteDao.save(utente);
    }
}
