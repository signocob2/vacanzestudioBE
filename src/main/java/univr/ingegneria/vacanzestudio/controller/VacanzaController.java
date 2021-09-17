package univr.ingegneria.vacanzestudio.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.*;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Questionario;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.VacanzaService;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vacanza")
class VacanzaController {
    private static final String DEFAULT_DATI_AGGIUNTIVI = "Non è ancora confermato nessun amico con cui alloggerai";

    @Resource
    VacanzaService vacanzaService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RicercaVacanzaDto> getAllVacanza() {
        List<Vacanza> vacanzaList = vacanzaService.findAllVacanza();
        return convertToRicercaVacanzaDtoList(vacanzaList);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DettaglioVacanzaDto getVacanzaById(@PathVariable("id") Long id) {
        return convertToDettaglioVacanzaDto(vacanzaService.findVacanzaById(id));
    }

    @GetMapping("/listaIdVacanzeGiaPrenotate/{idUtente}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Long> getListaIdVacanzeGiaPrenotate(@PathVariable("idUtente") Long idUtente) {
        List<Long> idVacanzaCollegeList = vacanzaService.findPrenotazioneVacanzaCollegeByIdUtente(idUtente)
                .stream()
                .map(p -> p.getVacanza().getId())
                .collect(Collectors.toList());

        List<Long> idVacanzaFamigliaList = vacanzaService.findPrenotazioneVacanzaFamigliaByIdUtente(idUtente)
                .stream()
                .map(p -> p.getVacanza().getId())
                .collect(Collectors.toList());

        return CollectionUtils.collate(idVacanzaCollegeList, idVacanzaFamigliaList);
    }

    @GetMapping("/listaVacanzePrenotate/{idUtente}/{dataCorrenteSimulata}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<VacanzaPrenotataDto> getListaVacanzePrenotate(@PathVariable("idUtente") Long idUtente, @PathVariable("dataCorrenteSimulata") String dataCorrenteSimulataString) {
        LocalDate dataCorrenteSimulata = LocalDate.parse(dataCorrenteSimulataString, DateTimeFormatter.BASIC_ISO_DATE);

        List<PrenotazioneVacanzaCollege> prenotazioneVacanzaCollegeList = vacanzaService.findPrenotazioneVacanzaCollegeByIdUtente(idUtente);
        List<PrenotazioneVacanzaFamiglia> prenotazioneVacanzaFamigliaList = vacanzaService.findPrenotazioneVacanzaFamigliaByIdUtente(idUtente);

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

        vacanzePrenotateFamiglia.forEach(cf -> {
            if (Objects.nonNull(vacanzaService.findPrenotazioneVacanzaFamigliaByVacanzaIdAndEmailAmico(cf.getId(), cf.getEmailAmico()))) {
                cf.setDettagliAggiuntivi("Confermiamo che nella stessa famiglia soggiornerà anche l'amico " + cf.getNomeAmico() + " - " + cf.getEmailAmico());
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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public VacanzaDto addVacanza(@RequestBody VacanzaDto vacanzaDto) {
        Vacanza vacanza = convertToVacanzaEntity(vacanzaDto);
        Vacanza newVacanza = vacanzaService.addVacanza(vacanza);
        return convertToVacanzaDto(newVacanza);
    }

    @PostMapping("/prenotazione/addVacanzaCollege")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PrenotazioneVacanzaCollegeDto addPrenotazioneVacanzaCollege(@RequestBody PrenotazioneVacanzaCollegeDto prenotazioneVacanzaCollegeDto) {
        PrenotazioneVacanzaCollege prenotazioneVacanzaCollege = convertToPrenotazioneVacanzaCollegeEntity(prenotazioneVacanzaCollegeDto);
        prenotazioneVacanzaCollege = vacanzaService.addVacanzaCollege(prenotazioneVacanzaCollege);
        return convertToPrenotazioneVacanzaCollegeDto(prenotazioneVacanzaCollege);
    }

    @PostMapping("/prenotazione/addVacanzaFamiglia")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public PrenotazioneVacanzaFamigliaDto addPrenotazioneVacanzaFamiglia(@RequestBody PrenotazioneVacanzaFamigliaDto prenotazioneVacanzaFamigliaDto) {
        PrenotazioneVacanzaFamiglia prenotazioneVacanzaFamiglia = convertToPrenotazioneVacanzaFamigliaEntity(prenotazioneVacanzaFamigliaDto);
        prenotazioneVacanzaFamiglia = vacanzaService.addVacanzaFamiglia(prenotazioneVacanzaFamiglia);
        return convertToPrenotazioneVacanzaFamigliaDto(prenotazioneVacanzaFamiglia);
    }

    // Converter to dto
    private VacanzaDto convertToVacanzaDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, VacanzaDto.class);
    }

    private DettaglioVacanzaDto convertToDettaglioVacanzaDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, DettaglioVacanzaDto.class);
    }

    private RicercaVacanzaDto convertToRicercaVacanzaDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, RicercaVacanzaDto.class);
    }

    private List<RicercaVacanzaDto> convertToRicercaVacanzaDtoList(List<Vacanza> vacanzaList) {
        return vacanzaList.stream()
                .map(this::convertToRicercaVacanzaDto)
                .collect(Collectors.toList());
    }

    private PrenotazioneVacanzaCollegeDto convertToPrenotazioneVacanzaCollegeDto(PrenotazioneVacanzaCollege prenotazioneVacanzaCollege) {
        return modelMapper.map(prenotazioneVacanzaCollege, PrenotazioneVacanzaCollegeDto.class);
    }

    private PrenotazioneVacanzaFamigliaDto convertToPrenotazioneVacanzaFamigliaDto(PrenotazioneVacanzaFamiglia prenotazioneVacanzaFamiglia) {
        return modelMapper.map(prenotazioneVacanzaFamiglia, PrenotazioneVacanzaFamigliaDto.class);
    }

    // Converter to entity
    private Vacanza convertToVacanzaEntity(VacanzaDto vacanzaDto) {
        return modelMapper.map(vacanzaDto, Vacanza.class);
    }

    private PrenotazioneVacanzaCollege convertToPrenotazioneVacanzaCollegeEntity(PrenotazioneVacanzaCollegeDto prenotazioneVacanzaCollegeDto) {
        return modelMapper.map(prenotazioneVacanzaCollegeDto, PrenotazioneVacanzaCollege.class);
    }

    private PrenotazioneVacanzaFamiglia convertToPrenotazioneVacanzaFamigliaEntity(PrenotazioneVacanzaFamigliaDto prenotazioneVacanzaFamigliaDto) {
        return modelMapper.map(prenotazioneVacanzaFamigliaDto, PrenotazioneVacanzaFamiglia.class);
    }
}