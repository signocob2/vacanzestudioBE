package univr.ingegneria.vacanzestudio.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.UtenteException;
import univr.ingegneria.vacanzestudio.model.Utente;
import univr.ingegneria.vacanzestudio.repository.UtenteRepository;

import javax.annotation.Resource;

@Service
public class UtenteService {
    @Resource
    UtenteRepository utenteRepository;

    public Utente findUtenteById(Long idUtente) {
        return utenteRepository.findUtenteById(idUtente)
                .orElseThrow(() -> new UtenteException("Modifica non ammessa - Utente con id " + idUtente + " non trovato"));
    }

    public Utente addUtente(Utente utente) {
        return prepareAndSaveUtente(utente);
    }

    public Utente updateUtente(Utente newUtente) {
        // Recupero il vecchio utente
        Utente oldUtente = utenteRepository.findById(newUtente.getId())
                .orElseThrow(() -> new UtenteException("Modifica non effettuata - Utente con id " + newUtente.getId() + " non trovato"));

        // Controllo se la mail esiste già
        if (!StringUtils.equals(oldUtente.getEmail(), newUtente.getEmail())) {
            utenteRepository.findUtenteByEmail(newUtente.getEmail()).ifPresent(s -> {
                throw new UtenteException("Modifica non effettuata - Utente con email " + s.getEmail() + " già presente");
            });
        }

        newUtente.setId(oldUtente.getId());

        newUtente.setPrenotazioneVacanzaCollegeList(oldUtente.getPrenotazioneVacanzaCollegeList());
        newUtente.getPrenotazioneVacanzaCollegeList().forEach(p -> p.setUtente(newUtente));

        newUtente.setPrenotazioneVacanzaFamigliaList(oldUtente.getPrenotazioneVacanzaFamigliaList());
        newUtente.getPrenotazioneVacanzaFamigliaList().forEach(p -> p.setUtente(newUtente));

        utenteRepository.delete(oldUtente);
        return prepareAndSaveUtente(newUtente);
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

        return utenteRepository.save(utente);
    }
}
