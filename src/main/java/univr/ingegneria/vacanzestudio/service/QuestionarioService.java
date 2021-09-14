package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.QuestionarioException;
import univr.ingegneria.vacanzestudio.model.Questionario;
import univr.ingegneria.vacanzestudio.repository.QuestionarioRepository;

import javax.annotation.Resource;

@Service
public class QuestionarioService {
    @Resource
    QuestionarioRepository questionarioRepository;

    public Questionario findQuestionarioByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return questionarioRepository.findQuestionarioByUtenteIdAndVacanzaId(idUtente, idVacanza)
                .orElseThrow(() -> new QuestionarioException("Questionario per utente " + idUtente + " per vacanza" + idVacanza + " non trovato"));
    }

    public Questionario compilaQuestionario(Questionario q) {
        return questionarioRepository.save(q);
    }
}
