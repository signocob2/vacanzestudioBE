package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.StudenteDto;
import univr.ingegneria.vacanzestudio.model.Studente;
import univr.ingegneria.vacanzestudio.service.LoginService;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping(value = "/login")
class LoginController {
    @Resource
    LoginService loginService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StudenteDto isAuthenticatedByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        Studente studente = loginService.isAuthenticatedByEmailAndPassword(email, password);
        if (Objects.nonNull(studente)) {
            return convertToStudenteDto(studente);
        } else {
            return null;
        }
    }

    private StudenteDto convertToStudenteDto(Studente studente) {
        return modelMapper.map(studente, StudenteDto.class);
    }
}