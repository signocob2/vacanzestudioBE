package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.DettaglioVacanzaDto;
import univr.ingegneria.vacanzestudio.dto.RicercaVacanzaDto;
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

    // Converter
    private DettaglioVacanzaDto convertToDettaglioVacanzaDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, DettaglioVacanzaDto.class);
    }

    private RicercaVacanzaDto convertToVacanzaDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, RicercaVacanzaDto.class);
    }

    private List<RicercaVacanzaDto> convertToRicercaVacanzaDtoList(List<Vacanza> vacanzaList) {
        return vacanzaList.stream()
                .map(this::convertToVacanzaDto)
                .collect(Collectors.toList());
    }
}