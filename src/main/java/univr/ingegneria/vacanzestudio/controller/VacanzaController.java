package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.VacanzaDto;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.VacanzaService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/vacanza")
class VacanzaController {
    @Resource
    VacanzaService vacanzaService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/all")
    public ResponseEntity<List<Vacanza>> getAllVacanza() {
        List<Vacanza> vacanzaList = vacanzaService.findAllVacanza();
        return new ResponseEntity<>(vacanzaList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public VacanzaDto getVacanzaById(@PathVariable("id") Long id) {
        return convertToDto(vacanzaService.findVacanzaById(id));
    }

    private VacanzaDto convertToDto(Vacanza vacanza) {
        return modelMapper.map(vacanza, VacanzaDto.class);
    }
}