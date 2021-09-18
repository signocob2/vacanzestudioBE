package univr.ingegneria.vacanzestudio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.SimulaAvanzamentoDto;
import univr.ingegneria.vacanzestudio.service.SimulaAvanzamentoService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/simulaAvanzamento")
class SimulaAvanzamentoController {
    @Resource
    SimulaAvanzamentoService simulaAvanzamentoService;

    @PostMapping("/simula")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void simula(@RequestBody SimulaAvanzamentoDto simulaAvanzamentoDto) {
        simulaAvanzamentoService.gestioneCertificati(simulaAvanzamentoDto.getDataCorrenteSimulata());
    }

}