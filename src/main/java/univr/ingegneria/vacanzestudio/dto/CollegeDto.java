package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollegeDto {
    private Long id;

    private String nome;

    private String indirizzo;

    private List<AttivitaCollegeDto> attivitaCollegeList;
}
