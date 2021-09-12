package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.CertificatoDto;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.service.CertificatoService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/certificato")
class CertificatoController {
    @Resource
    CertificatoService certificatoService;

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CertificatoDto> getCertificatoByIdUtente(@PathVariable("id") Long id) {
        return certificatoService.findAllCertificatoByUtenteId(id)
                .stream()
                .map(c -> {
                    Vacanza vacanza = c.getVacanza();
                    return new CertificatoDto(c.getId(), vacanza.getId(), vacanza.getCittaDiPermanenza(), vacanza.getLinguaStranieraStudiata(), c.getLivelloRaggiunto());
                }).collect(Collectors.toList());
    }

}