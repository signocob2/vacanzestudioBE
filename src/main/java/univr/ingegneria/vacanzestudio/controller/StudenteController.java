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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public StudenteDto addStudente(@RequestBody StudenteDto studenteDto) {
        Studente studente = convertToEntity(studenteDto);
        Studente newStudente = studenteService.addStudente(studente);
        return convertToDto(newStudente);
    }

    private StudenteDto convertToDto(Studente studente) {
        return modelMapper.map(studente, StudenteDto.class);
    }

    private Studente convertToEntity(StudenteDto studenteDto) {
        return modelMapper.map(studenteDto, Studente.class);
    }
}