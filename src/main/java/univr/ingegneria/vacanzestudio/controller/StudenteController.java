package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.StudenteDto;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.service.StudenteService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/studente")
class StudenteController {
    @Resource
    StudenteService studenteService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StudenteDto getStudenteById(@PathVariable("id") Long id) {
        return convertToStudenteDto(studenteService.findStudenteById(id));
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StudenteDto addStudente(@RequestBody StudenteDto studenteDto) {
        Studente studente = convertToEntity(studenteDto);
        Studente newStudente = studenteService.addStudente(studente);
        return convertToStudenteDto(newStudente);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StudenteDto updateStudente(@RequestBody StudenteDto studenteDto) {
        Studente studente = convertToEntity(studenteDto);
        Studente newStudente = studenteService.updateStudente(studente);
        return convertToStudenteDto(newStudente);
    }

    private StudenteDto convertToStudenteDto(Studente studente) {
        return modelMapper.map(studente, StudenteDto.class);
    }

    private Studente convertToEntity(StudenteDto studenteDto) {
        return modelMapper.map(studenteDto, Studente.class);
    }
}