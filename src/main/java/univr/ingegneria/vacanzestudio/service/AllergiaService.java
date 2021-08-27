package univr.ingegneria.vacanzestudio.service;

import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.exception.AllergiaNotFoundException;
import univr.ingegneria.vacanzestudio.model.Allergia;
import univr.ingegneria.vacanzestudio.repository.AllergiaRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AllergiaService {
    @Resource
    private AllergiaRepository allergiaRepository;

    public List<Allergia> findAllAllergia() {
        return allergiaRepository.findAll();
    }

    public Allergia findAllergiaById(Long id) {
        return allergiaRepository.findAllergiaById(id)
                .orElseThrow(() -> new AllergiaNotFoundException("Allergia by id" + id + " was not found"));
    }

    public Allergia addAllergia(Allergia allergia) {
        allergia.setId(0L);
        return allergiaRepository.save(allergia);
    }

    public Allergia updateAllergia(Allergia allergia) {
        return allergiaRepository.save(allergia);
    }

    public void deleteAllergia(Long id) {
        allergiaRepository.deleteAllergiaById(id);
    }
}
