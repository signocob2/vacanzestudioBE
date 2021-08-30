package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.service.LoginService;
import univr.ingegneria.vacanzestudio.service.StudenteService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/studente")
class StudenteController {
    @Resource
    StudenteService studenteService;

    @PostMapping("/add")
    public ResponseEntity<Studente> addStudente(@RequestBody Studente studente) {
        Studente newStudente = studenteService.addStudente(studente);
        return new ResponseEntity<>(newStudente, HttpStatus.CREATED);
    }
}