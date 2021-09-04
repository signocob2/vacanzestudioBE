package univr.ingegneria.vacanzestudio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univr.ingegneria.vacanzestudio.service.AllergiaService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/allergia")
class AllergiaController {
    @Resource
    AllergiaService allergiaService;
//
//    @GetMapping("/all")
//    public ResponseEntity<List<Allergia>> getAllAllergia() {
//        List<Allergia> allergiaList = allergiaService.findAllAllergia();
//        return new ResponseEntity<>(allergiaList, HttpStatus.OK);
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<Allergia> getAllergiaById(@PathVariable("id") Long id) {
//        Allergia allergia = allergiaService.findAllergiaById(id);
//        return new ResponseEntity<>(allergia, HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Allergia> addAllergia(@RequestBody Allergia allergia) {
//        Allergia newAllergia = allergiaService.addAllergia(allergia);
//        return new ResponseEntity<>(newAllergia, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Allergia> updateAllergia(@RequestBody Allergia allergia) {
//        Allergia updatedAllergia = allergiaService.updateAllergia(allergia);
//        return new ResponseEntity<>(updatedAllergia, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteAllergia(@PathVariable("id") Long id) {
//        allergiaService.deleteAllergia(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}