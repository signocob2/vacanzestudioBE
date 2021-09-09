package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.UtenteDto;
import univr.ingegneria.vacanzestudio.model.Utente;
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
    public UtenteDto isAuthenticatedByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        Utente utente = loginService.isAuthenticatedByEmailAndPassword(email, password);
        if (Objects.nonNull(utente)) {
            return convertToUtenteDto(utente);
        } else {
            return null;
        }
    }

    private UtenteDto convertToUtenteDto(Utente utente) {
        return modelMapper.map(utente, UtenteDto.class);
    }
}