package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.VacanzaException;
import univr.ingegneria.vacanzestudio.model.Vacanza;
import univr.ingegneria.vacanzestudio.repository.VacanzaRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VacanzaService {
    @Resource
    private VacanzaRepository vacanzaRepository;

    public List<Vacanza> findAllVacanza() {
        return vacanzaRepository.findAll();
    }

    public Vacanza findVacanzaById(Long id) {
        return vacanzaRepository.findVacanzaById(id)
                .orElseThrow(() -> new VacanzaException("Vacanza con id" + id + " non trovata"));
    }
}
