package univr.ingegneria.vacanzestudio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import univr.ingegneria.vacanzestudio.dao.UtenteDao;
import univr.ingegneria.vacanzestudio.exception.UtenteException;
import univr.ingegneria.vacanzestudio.model.Utente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtenteServiceTest {

    public static final String NOME = "NomeTest";
    public static final String COGNOME = "CognomeTest";
    public static final LocalDate DATA_DI_NASCITA = LocalDate.of(2001, 1, 1);
    public static final String COMUNE_DI_NASCITA = "ComuneTest";
    public static final String PROVINCIA_DI_NASCITA = "ProvinciaTest";
    public static final String CODICE_FISCALE = "TSTTST95T24E349R";
    public static final String INDIRIZZO = "IndirizzoTest";
    public static final String RECAPITO_TELEFONICO = "3401112223";
    public static final String EMAIL = "emailTest";
    public static final String PASSWORD = "passwordTest";
    public static final String IS_ADMIN = "N";

    @Mock
    UtenteDao utenteDaoMock;

    @Captor
    ArgumentCaptor<Utente> utenteCaptor;

    @Test
    void addUtenteMailLibereECodiceFiscaleLibero() {
        // Prepara i mock per il test
        Utente utenteTest = creaUtenteTestConTutteLeListeVuote();

        mockEmailUtenteLibera(utenteTest);
        mockCodiceFiscaleUtenteLibero(utenteTest);

        UtenteService utenteService = creaUtenteService();

        // Chiama il metodo da testare
        utenteService.addUtente(utenteTest);

        // Verifica l'esito del test
        verify(utenteDaoMock, times(1)).save(utenteCaptor.capture());
        Utente utenteUsatoInSalvataggio = utenteCaptor.getValue();
        assertEquals(NOME, utenteUsatoInSalvataggio.getNome());
        assertEquals(COGNOME, utenteUsatoInSalvataggio.getCognome());
        assertEquals(DATA_DI_NASCITA, utenteUsatoInSalvataggio.getDataDiNascita());
        assertEquals(COMUNE_DI_NASCITA, utenteUsatoInSalvataggio.getComuneDiNascita());
        assertEquals(PROVINCIA_DI_NASCITA, utenteUsatoInSalvataggio.getProvinciaDiNascita());
        assertEquals(CODICE_FISCALE, utenteUsatoInSalvataggio.getCodiceFiscale());
        assertEquals(INDIRIZZO, utenteUsatoInSalvataggio.getIndirizzo());
        assertEquals(RECAPITO_TELEFONICO, utenteUsatoInSalvataggio.getRecapitoTelefonico());
        assertEquals(EMAIL, utenteUsatoInSalvataggio.getEmail());
        assertEquals(PASSWORD, utenteUsatoInSalvataggio.getPassword());
        assertEquals(IS_ADMIN, utenteUsatoInSalvataggio.getIsAdmin());

        assertTrue(utenteUsatoInSalvataggio.getAllergiaList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getHobbyList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getGenitoreList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getPrenotazioneVacanzaCollegeList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getPrenotazioneVacanzaFamigliaList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getQuestionarioList().isEmpty());
        assertTrue(utenteUsatoInSalvataggio.getCertificatoList().isEmpty());

        assertEquals(utenteUsatoInSalvataggio.getEmail(), utenteUsatoInSalvataggio.getUtente_inserimento());
        assertEquals(utenteUsatoInSalvataggio.getEmail(), utenteUsatoInSalvataggio.getUtente_modifica());
    }

    @Test
    void addUtenteConMailOccupata() {
        // Prepara i mock per il test
        Utente utenteTest = creaUtenteTestConTutteLeListeVuote();

        mockEmailUtenteOccupata(utenteTest);

        UtenteService utenteService = creaUtenteService();

        // Chiama il metodo da testare
        UtenteException thrown = assertThrows(UtenteException.class, () ->
                utenteService.addUtente(utenteTest));

        // Verifica l'esito del test
        verify(utenteDaoMock, times(0)).save(Mockito.any());
        assertEquals("Errore - Utente con email " + utenteTest.getEmail() + " già presente", thrown.getMessage());
    }

    private Utente creaUtenteTestConTutteLeListeVuote() {
        Utente utenteTest = new Utente();
        utenteTest.setNome(NOME);
        utenteTest.setCognome(COGNOME);
        utenteTest.setDataDiNascita(DATA_DI_NASCITA);
        utenteTest.setComuneDiNascita(COMUNE_DI_NASCITA);
        utenteTest.setProvinciaDiNascita(PROVINCIA_DI_NASCITA);
        utenteTest.setCodiceFiscale(CODICE_FISCALE);
        utenteTest.setIndirizzo(INDIRIZZO);
        utenteTest.setRecapitoTelefonico(RECAPITO_TELEFONICO);
        utenteTest.setEmail(EMAIL);
        utenteTest.setPassword(PASSWORD);
        utenteTest.setIsAdmin(IS_ADMIN);
        utenteTest.setAllergiaList(new ArrayList<>());
        utenteTest.setHobbyList(new ArrayList<>());
        utenteTest.setGenitoreList(new ArrayList<>());
        utenteTest.setPrenotazioneVacanzaCollegeList(new ArrayList<>());
        utenteTest.setPrenotazioneVacanzaFamigliaList(new ArrayList<>());
        utenteTest.setQuestionarioList(new ArrayList<>());
        utenteTest.setCertificatoList(new ArrayList<>());
        return utenteTest;
    }

    private UtenteService creaUtenteService() {
        UtenteService utenteService = new UtenteService();
        utenteService.setUtenteDao(utenteDaoMock);
        return utenteService;
    }

    private void mockEmailUtenteLibera(Utente utenteTest) {
        when(utenteDaoMock.findUtenteByEmail(utenteTest.getEmail())).thenReturn(Optional.empty());
    }

    private void mockEmailUtenteOccupata(Utente utenteTest) {
        Utente utente = new Utente();
        utente.setEmail(EMAIL);
        when(utenteDaoMock.findUtenteByEmail(utenteTest.getEmail())).thenReturn(Optional.of(utente));
    }

    private void mockCodiceFiscaleUtenteLibero(Utente utenteTest) {
        when(utenteDaoMock.findUtenteByCodiceFiscale(utenteTest.getCodiceFiscale())).thenReturn(Optional.empty());
    }

}