package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.service.LoginService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/login")
class LoginController {
    @Resource
    LoginService loginServico;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Boolean getAllAllergia(@RequestParam String email, @RequestParam String password) {
        return loginServico.isAuthenticatedByEmailAndPassword(email, password);
    }
}