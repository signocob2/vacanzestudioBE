package univr.ingegneria.vacanzestudio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univr.ingegneria.vacanzestudio.service.AllergiaService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/allergia")
class AllergiaController {
    @Resource
    AllergiaService allergiaService;

    @GetMapping()
    public void findAllAllergia() {
        System.out.println("Allergie: " + allergiaService.findAllAllergia());
    }
}