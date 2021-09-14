package univr.ingegneria.vacanzestudio.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import univr.ingegneria.vacanzestudio.dto.QuestionarioDto;
import univr.ingegneria.vacanzestudio.model.Questionario;
import univr.ingegneria.vacanzestudio.service.QuestionarioService;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/questionario")
class QuestionarioController {
    @Resource
    QuestionarioService questionarioService;

    @Resource
    ModelMapper modelMapper;

    @GetMapping("/find/{idStudente}/{idVacanza}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public QuestionarioDto getQuestionarioByIdUtenteAndIdVacanza(@PathVariable("idStudente") Long idStudente, @PathVariable("idVacanza") Long idVacanza) {
        Questionario q = questionarioService.findQuestionarioByUtenteIdAndVacanzaId(idStudente, idVacanza);
        return new QuestionarioDto(idStudente, idVacanza, q.getEsperienzaPositiva(), q.getAlloggioCurato(), q.getPersonaleDisponibile(), q.getUtilePerLingua(), q.getPrezzoGiteAppropriato(), q.getVotoGradimento(), q.getCommentoLibero(), q.getIsCompilato());
    }

    @PostMapping("/compila")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public QuestionarioDto compilaQuestionario(@RequestBody QuestionarioDto questionarioDto) {
        Questionario questionario = convertToQuestionarioEntity(questionarioDto);
        questionario.getVacanza().setId(questionarioDto.getIdVacanza());
        questionario.getUtente().setId(questionarioDto.getIdUtente());

        questionario = questionarioService.compilaQuestionario(questionario);
        return convertToQuestionarioDto(questionario);
    }

    // Converter to dto
    private QuestionarioDto convertToQuestionarioDto(Questionario questionario) {
        return modelMapper.map(questionario, QuestionarioDto.class);
    }

    // Converter to entity
    private Questionario convertToQuestionarioEntity(QuestionarioDto questionarioDto) {
        return modelMapper.map(questionarioDto, Questionario.class);
    }
}