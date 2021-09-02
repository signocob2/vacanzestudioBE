package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.VacanzaService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/vacanza")
class VacanzaController {
    @Resource
    VacanzaService vacanzaService;

    @GetMapping("/all")
    public ResponseEntity<List<Vacanza>> getAllVacanza() {
        List<Vacanza> vacanzaList = vacanzaService.findAllVacanza();
        return new ResponseEntity<>(vacanzaList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Vacanza> getVacanzaById(@PathVariable("id") Long id) {
        Vacanza vacanza = vacanzaService.findVacanzaById(id);
        return new ResponseEntity<>(vacanza, HttpStatus.OK);
    }
}