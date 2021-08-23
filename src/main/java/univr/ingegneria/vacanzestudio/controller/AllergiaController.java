package univr.ingegneria.vacanzestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.repository.AllergiaRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/test")
class AllergiaController {

    @Autowired
    private AllergiaRepository repository;

    @GetMapping
    public void getTestData() {
        System.out.println("Sono entrato nel metodo");
        List<Allergia> allergiaList = new ArrayList<>(repository.findAll());
        System.out.println("Allergie: " + allergiaList);
    }
}