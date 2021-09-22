package univr.ingegneria.vacanzestudio.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import univr.ingegneria.vacanzestudio.dao.GenitoreDao;
import univr.ingegneria.vacanzestudio.dao.UtenteDao;
import univr.ingegneria.vacanzestudio.exception.GenitoreException;
import univr.ingegneria.vacanzestudio.exception.UtenteException;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.model.Genitore;
import univr.ingegneria.vacanzestudio.model.Hobby;
import univr.ingegneria.vacanzestudio.model.Utente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UtenteServiceTest {

    public static final String NOME = "UtenteNomeTest";
    public static final String COGNOME = "UtenteCognomeTest";
    public static final LocalDate DATA_DI_NASCITA = LocalDate.of(2001, 1, 1);
    public static final String COMUNE_DI_NASCITA = "UtenteComuneTest";
    public static final String PROVINCIA_DI_NASCITA = "UtenteProvinciaTest";
    public static final String CODICE_FISCALE = "TSTTST95T24E349R";
    public static final String INDIRIZZO = "IndirizzoTest";
    public static final String UTENTE_RECAPITO_TELEFONICO = "3401112223";
    public static final String UTENTE_EMAIL = "studenteemail@gmail.com";
    public static final String PASSWORD = "PasswordTest";
    public static final String IS_ADMIN = "N";
    public static final String NOME_ALLERGENE_1 = "NomeAllergene_1";
    public static final String DESCRIZIONE_PRECAUZIONI_NECESSARIE_1 = "DescrizionePrecauzioniNecessarie_1";
    public static final String NOME_ALLERGENE_2 = "NomeAllergene_2";
    public static final String DESCRIZIONE_PRECAUZIONI_NECESSARIE_2 = "DescrizionePrecauzioniNecessarie_2";
    public static final String HOBBY_1 = "Hobby_1";
    public static final String HOBBY_2 = "Hobby_2";
    public static final String GENITORE_NOME_1 = "GenitoreNome_1";
    public static final String GENITORE_COGNOME_1 = "GenitoreCognome_1";
    public static final String GENITORE_RECAPITO_TELEFONICO_1 = "3498887776";
    public static final String GENITORE_EMAIL_1 = "genitoreemail1@gmail.com";
    public static final String GENITORE_NOME_2 = "GenitoreNome_2";
    public static final String GENITORE_COGNOME_2 = "GenitoreCognome_2";
    public static final String GENITORE_RECAPITO_TELEFONICO_2 = "3498887775";
    public static final String GENITORE_EMAIL_2 = "genitoreemail2@gmail.com";

    @Mock
    UtenteDao utenteDaoMock;

    @Mock
    GenitoreDao genitoreDaoMock;

    @Captor
    ArgumentCaptor<Utente> utenteCaptor;

    @Test
    void addUtenteMailLibereECodiceFiscaleLiberoEConListeVuote() {
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
        assertEquals(UTENTE_RECAPITO_TELEFONICO, utenteUsatoInSalvataggio.getRecapitoTelefonico());
        assertEquals(UTENTE_EMAIL, utenteUsatoInSalvataggio.getEmail());
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
    void addUtenteMailLibereECodiceFiscaleLiberoEConAllergieHobbyEGenitori() {
        // Prepara i mock per il test
        Utente utenteTest = creaUtenteTestConAllergieHobbyEGenitori();

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
        assertEquals(UTENTE_RECAPITO_TELEFONICO, utenteUsatoInSalvataggio.getRecapitoTelefonico());
        assertEquals(UTENTE_EMAIL, utenteUsatoInSalvataggio.getEmail());
        assertEquals(PASSWORD, utenteUsatoInSalvataggio.getPassword());
        assertEquals(IS_ADMIN, utenteUsatoInSalvataggio.getIsAdmin());

        // Allergie
        assertEquals(2, utenteUsatoInSalvataggio.getAllergiaList().size());
        Allergia primaAllergia = utenteUsatoInSalvataggio.getAllergiaList().get(0);
        Allergia secondaAllergia = utenteUsatoInSalvataggio.getAllergiaList().get(1);
        assertEquals(NOME_ALLERGENE_1, primaAllergia.getNomeAllergene());
        assertEquals(DESCRIZIONE_PRECAUZIONI_NECESSARIE_1, primaAllergia.getDescrizionePrecauzioniNecessarie());
        assertEquals(NOME_ALLERGENE_2, secondaAllergia.getNomeAllergene());
        assertEquals(DESCRIZIONE_PRECAUZIONI_NECESSARIE_2, secondaAllergia.getDescrizionePrecauzioniNecessarie());

        // Hobby
        assertEquals(2, utenteUsatoInSalvataggio.getHobbyList().size());
        Hobby primoHobby = utenteUsatoInSalvataggio.getHobbyList().get(0);
        Hobby secondoHobby = utenteUsatoInSalvataggio.getHobbyList().get(1);
        assertEquals(HOBBY_1, primoHobby.getNome());
        assertEquals(HOBBY_2, secondoHobby.getNome());

        // Genitori
        assertEquals(2, utenteUsatoInSalvataggio.getGenitoreList().size());
        Genitore primoGenitore = utenteUsatoInSalvataggio.getGenitoreList().get(0);
        Genitore secondoGenitore = utenteUsatoInSalvataggio.getGenitoreList().get(1);
        assertEquals(GENITORE_NOME_1, primoGenitore.getNome());
        assertEquals(GENITORE_COGNOME_1, primoGenitore.getCognome());
        assertEquals(GENITORE_RECAPITO_TELEFONICO_1, primoGenitore.getRecapitoTelefonico());
        assertEquals(GENITORE_EMAIL_1, primoGenitore.getEmail());
        assertEquals(GENITORE_NOME_2, secondoGenitore.getNome());
        assertEquals(GENITORE_COGNOME_2, secondoGenitore.getCognome());
        assertEquals(GENITORE_RECAPITO_TELEFONICO_2, secondoGenitore.getRecapitoTelefonico());
        assertEquals(GENITORE_EMAIL_2, secondoGenitore.getEmail());

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

    @Test
    void addUtenteConCodiceFiscaleOccupato() {
        // Prepara i mock per il test
        Utente utenteTest = creaUtenteTestConTutteLeListeVuote();

        mockEmailUtenteLibera(utenteTest);
        mockCodiceFiscaleUtenteOccupato(utenteTest);

        UtenteService utenteService = creaUtenteService();

        // Chiama il metodo da testare
        UtenteException thrown = assertThrows(UtenteException.class, () ->
                utenteService.addUtente(utenteTest));

        // Verifica l'esito del test
        verify(utenteDaoMock, times(0)).save(Mockito.any());
        assertEquals("Errore - Utente con codice fiscale " + utenteTest.getCodiceFiscale() + " già presente", thrown.getMessage());
    }

    @Test
    void addUtenteConGenitoreCheHaEmailOccupata() {
        // Prepara i mock per il test
        Utente utenteTest = creaUtenteTestConAllergieHobbyEGenitori();

        mockEmailUtenteLibera(utenteTest);
        mockCodiceFiscaleUtenteLibero(utenteTest);
        mockEmailGenitoreOccupata(utenteTest.getGenitoreList().get(0));

        UtenteService utenteService = creaUtenteService();

        // Chiama il metodo da testare
        GenitoreException thrown = assertThrows(GenitoreException.class, () ->
                utenteService.addUtente(utenteTest));

        // Verifica l'esito del test
        verify(utenteDaoMock, times(0)).save(Mockito.any());
        assertEquals("Errore - Genitore con email " + utenteTest.getGenitoreList().get(0).getEmail() + " già presente", thrown.getMessage());
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
        utenteTest.setRecapitoTelefonico(UTENTE_RECAPITO_TELEFONICO);
        utenteTest.setEmail(UTENTE_EMAIL);
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

    private Utente creaUtenteTestConAllergieHobbyEGenitori() {
        Utente utenteTest = new Utente();
        utenteTest.setNome(NOME);
        utenteTest.setCognome(COGNOME);
        utenteTest.setDataDiNascita(DATA_DI_NASCITA);
        utenteTest.setComuneDiNascita(COMUNE_DI_NASCITA);
        utenteTest.setProvinciaDiNascita(PROVINCIA_DI_NASCITA);
        utenteTest.setCodiceFiscale(CODICE_FISCALE);
        utenteTest.setIndirizzo(INDIRIZZO);
        utenteTest.setRecapitoTelefonico(UTENTE_RECAPITO_TELEFONICO);
        utenteTest.setEmail(UTENTE_EMAIL);
        utenteTest.setPassword(PASSWORD);
        utenteTest.setIsAdmin(IS_ADMIN);

        utenteTest.setAllergiaList(getListaAllergie(utenteTest));
        utenteTest.setHobbyList(getListaHobby(utenteTest));
        utenteTest.setGenitoreList(getListaGenitori(utenteTest));

        // Non popolabili in fase di registrazione
        utenteTest.setPrenotazioneVacanzaCollegeList(new ArrayList<>());
        utenteTest.setPrenotazioneVacanzaFamigliaList(new ArrayList<>());
        utenteTest.setQuestionarioList(new ArrayList<>());
        utenteTest.setCertificatoList(new ArrayList<>());
        return utenteTest;
    }

    private List<Allergia> getListaAllergie(Utente utenteTest) {
        List<Allergia> allergiaList = new ArrayList<>();

        Allergia primaAllergia = new Allergia();
        primaAllergia.setUtente(utenteTest);
        primaAllergia.setNomeAllergene(NOME_ALLERGENE_1);
        primaAllergia.setDescrizionePrecauzioniNecessarie(DESCRIZIONE_PRECAUZIONI_NECESSARIE_1);
        allergiaList.add(primaAllergia);

        Allergia secondaAllergia = new Allergia();
        secondaAllergia.setUtente(utenteTest);
        secondaAllergia.setNomeAllergene(NOME_ALLERGENE_2);
        secondaAllergia.setDescrizionePrecauzioniNecessarie(DESCRIZIONE_PRECAUZIONI_NECESSARIE_2);
        allergiaList.add(secondaAllergia);

        return allergiaList;
    }

    private List<Hobby> getListaHobby(Utente utenteTest) {
        List<Hobby> hobbyList = new ArrayList<>();

        Hobby primoHobby = new Hobby();
        primoHobby.setUtente(utenteTest);
        primoHobby.setNome(HOBBY_1);
        hobbyList.add(primoHobby);

        Hobby secondoHobby = new Hobby();
        secondoHobby.setUtente(utenteTest);
        secondoHobby.setNome(HOBBY_2);
        hobbyList.add(secondoHobby);

        return hobbyList;
    }

    private List<Genitore> getListaGenitori(Utente utenteTest) {
        List<Genitore> genitoreList = new ArrayList<>();

        Genitore primoGenitore = new Genitore();
        primoGenitore.setUtente(utenteTest);
        primoGenitore.setNome(GENITORE_NOME_1);
        primoGenitore.setCognome(GENITORE_COGNOME_1);
        primoGenitore.setRecapitoTelefonico(GENITORE_RECAPITO_TELEFONICO_1);
        primoGenitore.setEmail(GENITORE_EMAIL_1);
        genitoreList.add(primoGenitore);

        Genitore secondoGenitore = new Genitore();
        secondoGenitore.setUtente(utenteTest);
        secondoGenitore.setNome(GENITORE_NOME_2);
        secondoGenitore.setCognome(GENITORE_COGNOME_2);
        secondoGenitore.setRecapitoTelefonico(GENITORE_RECAPITO_TELEFONICO_2);
        secondoGenitore.setEmail(GENITORE_EMAIL_2);
        genitoreList.add(secondoGenitore);

        return genitoreList;
    }

    private UtenteService creaUtenteService() {
        UtenteService utenteService = new UtenteService();
        utenteService.setUtenteDao(utenteDaoMock);
        utenteService.setGenitoreDao(genitoreDaoMock);
        return utenteService;
    }

    private void mockEmailUtenteLibera(Utente utenteTest) {
        when(utenteDaoMock.findUtenteByEmail(utenteTest.getEmail())).thenReturn(Optional.empty());
    }

    private void mockEmailUtenteOccupata(Utente utenteTest) {
        Utente utente = new Utente();
        utente.setEmail(utenteTest.getEmail());
        when(utenteDaoMock.findUtenteByEmail(utenteTest.getEmail())).thenReturn(Optional.of(utente));
    }

    private void mockEmailGenitoreOccupata(Genitore genitoreTest) {
        Genitore genitore = new Genitore();
        genitore.setEmail(genitoreTest.getEmail());
        when(genitoreDaoMock.findGenitoreByEmail(genitoreTest.getEmail())).thenReturn(Optional.of(genitore));
    }

    private void mockCodiceFiscaleUtenteOccupato(Utente utenteTest) {
        Utente utente = new Utente();
        utente.setCodiceFiscale(CODICE_FISCALE);
        when(utenteDaoMock.findUtenteByCodiceFiscale(utenteTest.getCodiceFiscale())).thenReturn(Optional.of(utente));
    }

    private void mockCodiceFiscaleUtenteLibero(Utente utenteTest) {
        when(utenteDaoMock.findUtenteByCodiceFiscale(utenteTest.getCodiceFiscale())).thenReturn(Optional.empty());
    }

}