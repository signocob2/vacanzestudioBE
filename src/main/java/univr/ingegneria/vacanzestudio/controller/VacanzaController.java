package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.*;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaCollege;
import univr.ingegneria.vacanzestudio.model.PrenotazioneVacanzaFamiglia;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.VacanzaService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vacanza")
class VacanzaController {
    @Resource
    VacanzaService vacanzaService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/listaVacanzeNonTerminate/{dataCorrenteSimulata}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RicercaVacanzaDto> getListaVacanzeNonIniziate(@PathVariable("dataCorrenteSimulata") String dataCorrenteSimulataString) {
        List<Vacanza> vacanzaList = vacanzaService.getListaVacanzeNonIniziate(dataCorrenteSimulataString);
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
        return vacanzaService.getListaIdVacanzeGiaPrenotate(idUtente);
    }

    @GetMapping("/listaVacanzePrenotate/{idUtente}/{dataCorrenteSimulata}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<VacanzaPrenotataDto> getListaVacanzePrenotate(@PathVariable("idUtente") Long idUtente, @PathVariable("dataCorrenteSimulata") String dataCorrenteSimulataString) {
        return vacanzaService.getVacanzaPrenotataDtoList(idUtente, dataCorrenteSimulataString);
    }

    @GetMapping("/listaVacanzeTerminate/{dataCorrenteSimulata}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<VacanzaTerminataDto> getListaVacanzeTerminate(@PathVariable("dataCorrenteSimulata") String dataCorrenteSimulataString) {
        return vacanzaService.getVacanzaTerminataDtoList(dataCorrenteSimulataString);
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