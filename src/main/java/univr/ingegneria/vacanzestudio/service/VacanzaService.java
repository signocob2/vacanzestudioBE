package univr.ingegneria.vacanzestudio.service;

import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.*;
import univr.ingegneria.vacanzestudio.dto.QuestionarioDto;
import univr.ingegneria.vacanzestudio.dto.VacanzaPrenotataDto;
import univr.ingegneria.vacanzestudio.dto.VacanzaTerminataDto;
import univr.ingegneria.vacanzestudio.exception.VacanzaException;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Questionario;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Setter
public class VacanzaService {
    private static final String DEFAULT_DATI_AGGIUNTIVI = "Non è ancora confermato nessun amico con cui alloggerai";

    @Resource
    private VacanzaDao vacanzaDao;

    @Resource
    private FamigliaDao famigliaDao;

    @Resource
    private CollegeDao collegeDao;

    @Resource
    private PrenotazioneVacanzaCollegeDao prenotazioneVacanzaCollegeDao;

    @Resource
    private PrenotazioneVacanzaFamigliaDao prenotazioneVacanzaFamigliaDao;

    public List<Vacanza> findAllVacanza() {
        return vacanzaDao.findAll();
    }

    public Vacanza findVacanzaById(Long id) {
        return vacanzaDao.findVacanzaById(id)
                .orElseThrow(() -> new VacanzaException("Vacanza con id" + id + " non trovata"));
    }

    public PrenotazioneVacanzaFamiglia findPrenotazioneVacanzaFamigliaByVacanzaIdAndUtenteEmail(Long id, String emailAmico) {
        return prenotazioneVacanzaFamigliaDao.findPrenotazioneVacanzaFamigliaByVacanzaIdAndUtenteEmail(id, emailAmico).orElse(null);
    }

    public Integer getNumeroCamereRimanenti(Vacanza vacanza) {
        return vacanza.getFamiglia().getNumeroCamereDisponibili() - prenotazioneVacanzaFamigliaDao.findPrenotazioneVacanzaFamigliaByVacanzaId(vacanza.getId()).size();
    }

    public List<Vacanza> getListaVacanzeNonIniziate(String dataCorrenteSimulataString) {
        LocalDate dataCorrenteSimulata = LocalDate.parse(dataCorrenteSimulataString, DateTimeFormatter.BASIC_ISO_DATE);

        return vacanzaDao.findAll().stream()
                .filter(v -> !isVacanzaIniziata(dataCorrenteSimulata, v))
                .collect(Collectors.toList());
    }

    public List<Long> getListaIdVacanzeGiaPrenotate(Long idUtente) {
        List<Long> idVacanzaCollegeList = this.findPrenotazioneVacanzaCollegeByIdUtente(idUtente)
                .stream()
                .map(p -> p.getVacanza().getId())
                .collect(Collectors.toList());

        List<Long> idVacanzaFamigliaList = this.findPrenotazioneVacanzaFamigliaByIdUtente(idUtente)
                .stream()
                .map(p -> p.getVacanza().getId())
                .collect(Collectors.toList());

        return CollectionUtils.collate(idVacanzaCollegeList, idVacanzaFamigliaList);
    }

    public List<VacanzaPrenotataDto> getVacanzaPrenotataDtoList(Long idUtente, String dataCorrenteSimulataString) {
        LocalDate dataCorrenteSimulata = LocalDate.parse(dataCorrenteSimulataString, DateTimeFormatter.BASIC_ISO_DATE);

        List<PrenotazioneVacanzaCollege> prenotazioneVacanzaCollegeList = this.findPrenotazioneVacanzaCollegeByIdUtente(idUtente);
        List<PrenotazioneVacanzaFamiglia> prenotazioneVacanzaFamigliaList = this.findPrenotazioneVacanzaFamigliaByIdUtente(idUtente);

        List<VacanzaPrenotataDto> vacanzePrenotateCollege = prenotazioneVacanzaCollegeList.stream()
                .map(p -> {
                    Vacanza v = p.getVacanza();
                    LocalDate dataFineVacanza = v.getDataDiPartenza().plusWeeks(v.getNumeroDiSettimaneDurata());
                    Boolean isVacanzaTerminata = dataFineVacanza.isBefore(dataCorrenteSimulata) || dataFineVacanza.isEqual(dataCorrenteSimulata);
                    Boolean isQuestionarioCompilato = isQuestionarioCompilato(idUtente, v);

                    return new VacanzaPrenotataDto(v.getId(), v.getCittaDiPermanenza(), v.getDataDiPartenza(), v.getNumeroDiSettimaneDurata(), v.getLinguaStranieraStudiata(), p.getSingolaCondivisa(), null, null, p.getPagamentoCartaBonifico(), "", isVacanzaTerminata, isQuestionarioCompilato);
                })
                .collect(Collectors.toList());


        List<VacanzaPrenotataDto> vacanzePrenotateFamiglia = prenotazioneVacanzaFamigliaList.stream()
                .map(p -> {
                    Vacanza v = p.getVacanza();
                    LocalDate dataFineVacanza = v.getDataDiPartenza().plusWeeks(v.getNumeroDiSettimaneDurata());
                    Boolean isVacanzaTerminata = dataFineVacanza.isBefore(dataCorrenteSimulata) || dataFineVacanza.isEqual(dataCorrenteSimulata);
                    Boolean isQuestionarioCompilato = isQuestionarioCompilato(idUtente, v);

                    return new VacanzaPrenotataDto(v.getId(), v.getCittaDiPermanenza(), v.getDataDiPartenza(), v.getNumeroDiSettimaneDurata(), v.getLinguaStranieraStudiata(), null, p.getNomeAmico(), p.getEmailAmico(), p.getPagamentoCartaBonifico(), DEFAULT_DATI_AGGIUNTIVI, isVacanzaTerminata, isQuestionarioCompilato);
                })
                .collect(Collectors.toList());

        vacanzePrenotateFamiglia.forEach(vacanzaF -> {
            if (Objects.nonNull(this.findPrenotazioneVacanzaFamigliaByVacanzaIdAndUtenteEmail(vacanzaF.getId(), vacanzaF.getEmailAmico()))) {
                vacanzaF.setDettagliAggiuntivi("Confermiamo che nella stessa famiglia soggiornerà anche l'amico " + vacanzaF.getNomeAmico());
            }
        });

        List<VacanzaPrenotataDto> vacanzePrenotateTotali = new ArrayList<>(vacanzePrenotateCollege);
        vacanzePrenotateTotali.addAll(vacanzePrenotateFamiglia);

        return vacanzePrenotateTotali;
    }

    private Boolean isQuestionarioCompilato(Long idUtente, Vacanza vacanza) {
        for (Questionario q : vacanza.getQuestionarioList()) {
            if (q.getUtente().getId().equals(idUtente) && StringUtils.equals(q.getIsCompilato(), "S")) {
                return true;
            }
        }
        return false;
    }

    public List<VacanzaTerminataDto> getVacanzaTerminataDtoList(String dataCorrenteSimulataString) {
        LocalDate dataCorrenteSimulata = LocalDate.parse(dataCorrenteSimulataString, DateTimeFormatter.BASIC_ISO_DATE);

        return vacanzaDao.findAll().stream()
                .filter(v -> isVacanzaTerminata(dataCorrenteSimulata, v))
                .map(v -> {
                    List<QuestionarioDto> questionarioDtoList = v.getQuestionarioList().stream()
                            .map(q -> new QuestionarioDto(q.getId(), q.getUtente().getId(), q.getUtente().getEmail(), q.getVacanza().getId(), q.getEsperienzaPositiva(), q.getAlloggioCurato(), q.getPersonaleDisponibile(), q.getUtilePerLingua(), q.getPrezzoGiteAppropriato(), q.getVotoGradimento(), q.getCommentoLibero(), q.getIsCompilato()))
                            .collect(Collectors.toList());

                    Double votoMedioQuestionari = getVotoMedioQuestionari(v);
                    return new VacanzaTerminataDto(v.getId(), v.getCittaDiPermanenza(), v.getDataDiPartenza(), v.getNumeroDiSettimaneDurata(), v.getLinguaStranieraStudiata(), votoMedioQuestionari, questionarioDtoList);
                })
                .collect(Collectors.toList());
    }

    private boolean isVacanzaIniziata(LocalDate dataCorrenteSimulata, Vacanza v) {
        return v.getDataDiPartenza().isBefore(dataCorrenteSimulata) || v.getDataDiPartenza().isEqual(dataCorrenteSimulata);
    }

    private boolean isVacanzaTerminata(LocalDate dataCorrenteSimulata, Vacanza v) {
        LocalDate dataFineVacanza = v.getDataDiPartenza().plusWeeks(v.getNumeroDiSettimaneDurata());
        return dataFineVacanza.isBefore(dataCorrenteSimulata) || dataFineVacanza.isEqual(dataCorrenteSimulata);
    }

    private double getVotoMedioQuestionari(Vacanza v) {
        return v.getQuestionarioList().stream().mapToDouble(q -> Double.parseDouble(q.getVotoGradimento())).average().orElse(0);
    }

    public Vacanza addVacanza(Vacanza vacanza) {
        return prepareAndSaveVacanza(vacanza);
    }

    public PrenotazioneVacanzaCollege addVacanzaCollege(PrenotazioneVacanzaCollege prenotazioneVacanzaCollege) {
        return prenotazioneVacanzaCollegeDao.save(prenotazioneVacanzaCollege);
    }

    public PrenotazioneVacanzaFamiglia addVacanzaFamiglia(PrenotazioneVacanzaFamiglia prenotazioneVacanzaFamiglia) {
        if (StringUtils.isBlank(prenotazioneVacanzaFamiglia.getEmailAmico()) || StringUtils.isBlank(prenotazioneVacanzaFamiglia.getNomeAmico())) {
            prenotazioneVacanzaFamiglia.setEmailAmico(StringUtils.SPACE);
            prenotazioneVacanzaFamiglia.setNomeAmico(StringUtils.SPACE);
        }
        return prenotazioneVacanzaFamigliaDao.save(prenotazioneVacanzaFamiglia);
    }

    public List<PrenotazioneVacanzaCollege> findPrenotazioneVacanzaCollegeByIdUtente(Long id) {
        return prenotazioneVacanzaCollegeDao.findPrenotazioneVacanzaCollegeByUtenteId(id);
    }

    public List<PrenotazioneVacanzaFamiglia> findPrenotazioneVacanzaFamigliaByIdUtente(Long id) {
        return prenotazioneVacanzaFamigliaDao.findPrenotazioneVacanzaFamigliaByUtenteId(id);
    }

    @Transactional
    public void deleteByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        prenotazioneVacanzaFamigliaDao.deleteByUtenteIdAndVacanzaId(idUtente, idVacanza);
        prenotazioneVacanzaCollegeDao.deleteByUtenteIdAndVacanzaId(idUtente, idVacanza);
    }

    private Vacanza prepareAndSaveVacanza(Vacanza vacanza) {
        if (Objects.isNull(vacanza.getFamiglia().getId())) {
            vacanza.getFamiglia().setId(0L);
            vacanza.setFamiglia(famigliaDao.save(vacanza.getFamiglia()));
        }

        if (Objects.isNull(vacanza.getCollege().getId())) {
            vacanza.getCollege().setId(0L);
            vacanza.getCollege().getAttivitaCollegeList().forEach(attivitaCollege -> {
                attivitaCollege.setCollege(vacanza.getCollege());
                attivitaCollege.setUtente_inserimento(vacanza.getUtente_inserimento());
                attivitaCollege.setUtente_modifica(vacanza.getUtente_modifica());
            });
            vacanza.setCollege(collegeDao.save(vacanza.getCollege()));
        }

        vacanza.setUtente_inserimento(vacanza.getUtente_inserimento());
        vacanza.setUtente_modifica(vacanza.getUtente_inserimento());

        // Gite
        vacanza.getGitaList().forEach(gita -> {
            gita.setVacanza(vacanza);
            gita.setUtente_inserimento(vacanza.getUtente_inserimento());
            gita.setUtente_modifica(vacanza.getUtente_modifica());
        });

        return vacanzaDao.save(vacanza);
    }
}
