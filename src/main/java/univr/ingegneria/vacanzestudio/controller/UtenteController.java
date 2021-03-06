package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.UtenteDto;
import univr.ingegneria.vacanzestudio.exception.GenitoreException;
import univr.ingegneria.vacanzestudio.exception.UtenteException;
import univr.ingegneria.vacanzestudio.model.Utente;
import univr.ingegneria.vacanzestudio.service.UtenteService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/utente")
class UtenteController {
    @Resource
    UtenteService utenteService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUtenteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(convertToUtenteDto(utenteService.findUtenteById(id)));
        } catch (UtenteException e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<?> addUtente(@RequestBody UtenteDto utenteDto) {
        Utente utente = convertToEntity(utenteDto);
        try {
            Utente newUtente = utenteService.addUtente(utente);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToUtenteDto(newUtente));
        } catch (UtenteException | GenitoreException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<?> updateUtente(@RequestBody UtenteDto utenteDto) {
        Utente utente = convertToEntity(utenteDto);
        try {
            Utente newUtente = utenteService.updateUtente(utente);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToUtenteDto(newUtente));
        } catch (UtenteException | GenitoreException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    private UtenteDto convertToUtenteDto(Utente utente) {
        return modelMapper.map(utente, UtenteDto.class);
    }

    private Utente convertToEntity(UtenteDto utenteDto) {
        return modelMapper.map(utenteDto, Utente.class);
    }
}