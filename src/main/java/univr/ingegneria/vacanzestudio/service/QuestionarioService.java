package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.QuestionarioDao;
import univr.ingegneria.vacanzestudio.exception.QuestionarioException;
import univr.ingegneria.vacanzestudio.model.Questionario;

import javax.annotation.Resource;

@Service
public class QuestionarioService {
    @Resource
    QuestionarioDao questionarioDao;

    public Questionario findQuestionarioByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return questionarioDao.findQuestionarioByUtenteIdAndVacanzaId(idUtente, idVacanza)
                .orElseThrow(() -> new QuestionarioException("Questionario per utente " + idUtente + " per vacanza" + idVacanza + " non trovato"));
    }

    public Questionario compilaQuestionario(Questionario q) {
        return questionarioDao.save(q);
    }
}
