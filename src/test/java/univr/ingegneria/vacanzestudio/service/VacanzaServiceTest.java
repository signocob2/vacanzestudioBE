package univr.ingegneria.vacanzestudio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import univr.ingegneria.vacanzestudio.dao.PrenotazioneVacanzaCollegeDao;
import univr.ingegneria.vacanzestudio.dao.PrenotazioneVacanzaFamigliaDao;
import univr.ingegneria.vacanzestudio.dao.VacanzaDao;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VacanzaServiceTest {

    public static final String DATA_CORRENTE_SIMULATA_STRING_2021_09_01 = "20210901";
    public static final String DATA_CORRENTE_SIMULATA_STRING_2021_09_11 = "20210911";
    public static final String DATA_CORRENTE_SIMULATA_STRING_2021_09_21 = "20210921";

    public static final String CITTA_DI_PERMANENZA_1 = "cittaDiPermanenza1";
    public static final LocalDate DATA_DI_PARTENZA_1 = LocalDate.of(2021, 9, 6);
    public static final int NUMERO_DI_SETTIMANE_DURATA_1 = 1;
    public static final String LINGUA_STRANIERA_STUDIATA_1 = "linguaStranieraStudiata1";

    public static final String CITTA_DI_PERMANENZA_2 = "cittaDiPermanenza2";
    public static final LocalDate DATA_DI_PARTENZA_2 = LocalDate.of(2021, 9, 13);
    public static final int NUMERO_DI_SETTIMANE_DURATA_2 = 2;
    public static final String LINGUA_STRANIERA_STUDIATA_2 = "linguaStranieraStudiata2";

    @Mock
    VacanzaDao vacanzaDaoMock;

    @Mock
    PrenotazioneVacanzaCollegeDao prenotazioneVacanzaCollegeDao;

    @Mock
    PrenotazioneVacanzaFamigliaDao prenotazioneVacanzaFamigliaDao;

    @BeforeEach
    private void mockListaVacanzePresentiInDb() {
        when(vacanzaDaoMock.findAll()).thenReturn(getListaVacanzeTotali());
    }

    @Test
    void getListaVacanzeNonIniziate_NessunaGiaIniziata() {
        // Prepara i mock per il test
        mockListaVacanzePresentiInDb();

        VacanzaService vacanzaService = creaVacanzaService();

        // Chiama il metodo da testare
        List<Vacanza> vacanzaList = vacanzaService.getListaVacanzeNonIniziate(DATA_CORRENTE_SIMULATA_STRING_2021_09_01);

        // Verifica l'esito del test
        assertEquals(2, vacanzaList.size());
        Vacanza primaVacanza = vacanzaList.get(0);
        assertEquals(CITTA_DI_PERMANENZA_1, primaVacanza.getCittaDiPermanenza());
        assertEquals(DATA_DI_PARTENZA_1, primaVacanza.getDataDiPartenza());
        assertEquals(NUMERO_DI_SETTIMANE_DURATA_1, primaVacanza.getNumeroDiSettimaneDurata());
        assertEquals(LINGUA_STRANIERA_STUDIATA_1, primaVacanza.getLinguaStranieraStudiata());
        Vacanza secondaVacanza = vacanzaList.get(1);
        assertEquals(CITTA_DI_PERMANENZA_2, secondaVacanza.getCittaDiPermanenza());
        assertEquals(DATA_DI_PARTENZA_2, secondaVacanza.getDataDiPartenza());
        assertEquals(NUMERO_DI_SETTIMANE_DURATA_2, secondaVacanza.getNumeroDiSettimaneDurata());
        assertEquals(LINGUA_STRANIERA_STUDIATA_2, secondaVacanza.getLinguaStranieraStudiata());
    }

    @Test
    void getListaVacanzeNonIniziate_UnaIniziataEUnaNonIniziata() {
        // Prepara i mock per il test
        mockListaVacanzePresentiInDb();

        VacanzaService vacanzaService = creaVacanzaService();

        // Chiama il metodo da testare
        List<Vacanza> vacanzaList = vacanzaService.getListaVacanzeNonIniziate(DATA_CORRENTE_SIMULATA_STRING_2021_09_11);

        // Verifica l'esito del test
        assertEquals(1, vacanzaList.size());
        Vacanza primaVacanza = vacanzaList.get(0);
        assertEquals(CITTA_DI_PERMANENZA_2, primaVacanza.getCittaDiPermanenza());
        assertEquals(DATA_DI_PARTENZA_2, primaVacanza.getDataDiPartenza());
        assertEquals(NUMERO_DI_SETTIMANE_DURATA_2, primaVacanza.getNumeroDiSettimaneDurata());
        assertEquals(LINGUA_STRANIERA_STUDIATA_2, primaVacanza.getLinguaStranieraStudiata());
    }

    @Test
    void getListaVacanzeNonIniziate_TutteGiaIniziate() {
        // Prepara i mock per il test
        mockListaVacanzePresentiInDb();

        VacanzaService vacanzaService = creaVacanzaService();

        // Chiama il metodo da testare
        List<Vacanza> vacanzaList = vacanzaService.getListaVacanzeNonIniziate(DATA_CORRENTE_SIMULATA_STRING_2021_09_21);

        // Verifica l'esito del test
        assertTrue(vacanzaList.isEmpty());
    }

    @Test
    void getListaIdVacanzeGiaPrenotate_NessunaPrenotazioneTrovata() {
    }

    @Test
    void getListaIdVacanzeGiaPrenotate_PrenotazioniCollegeTrovate() {
    }

    @Test
    void getListaIdVacanzeGiaPrenotate_PrenotazioniFamigliaTrovate() {
    }

    @Test
    void getListaIdVacanzeGiaPrenotate_PrenotazioniCollegeEFamigliaTrovate() {
    }

    private List<Vacanza> getListaVacanzeTotali() {
        List<Vacanza> vacanzaList = new ArrayList<>();

        Vacanza primaVacanza = new Vacanza();
        primaVacanza.setCittaDiPermanenza(CITTA_DI_PERMANENZA_1);
        primaVacanza.setDataDiPartenza(DATA_DI_PARTENZA_1);
        primaVacanza.setNumeroDiSettimaneDurata(NUMERO_DI_SETTIMANE_DURATA_1);
        primaVacanza.setLinguaStranieraStudiata(LINGUA_STRANIERA_STUDIATA_1);
        vacanzaList.add(primaVacanza);

        Vacanza secondaVacanza = new Vacanza();
        secondaVacanza.setCittaDiPermanenza(CITTA_DI_PERMANENZA_2);
        secondaVacanza.setDataDiPartenza(DATA_DI_PARTENZA_2);
        secondaVacanza.setNumeroDiSettimaneDurata(NUMERO_DI_SETTIMANE_DURATA_2);
        secondaVacanza.setLinguaStranieraStudiata(LINGUA_STRANIERA_STUDIATA_2);
        vacanzaList.add(secondaVacanza);

        return vacanzaList;
    }

    private void mockPrenotazioneCollegeVuote(Long id) {
        when(prenotazioneVacanzaCollegeDao.findPrenotazioneVacanzaCollegeByUtenteId(id)).thenReturn(Collections.emptyList());
    }

    private VacanzaService creaVacanzaService() {
        VacanzaService vacanzaService = new VacanzaService();
        vacanzaService.setVacanzaDao(vacanzaDaoMock);
        return vacanzaService;
    }
}