package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.service.AllergiaService;
import univr.ingegneria.vacanzestudio.service.LoginService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/login")
class LoginController {
    @Resource
    LoginService loginService;

    @GetMapping
    public ResponseEntity<Boolean> getAllAllergia(@RequestParam String email, @RequestParam String password) {
        Boolean isAuthenticated = loginService.isAuthenticatedByEmailAndPassword(email, password);
        return new ResponseEntity<>(isAuthenticated, HttpStatus.OK);
    }
}