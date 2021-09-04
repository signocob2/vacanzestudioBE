package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CollegeDto extends BaseDto {
    private Long id;

    private String nome;

    private String indirizzo;

    private List<AttivitaCollegeDto> attivitaCollegeList;

    @JsonIgnore
    private List<VacanzaDto> vacanzaList;
}
