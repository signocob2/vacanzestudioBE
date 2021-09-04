package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrenotazioneVacanzaCollegeDto extends BaseDto {
    private Long id;

    private String singolaCondivisa;

    @JsonIgnore
    private VacanzaDto vacanza;

    @JsonIgnore
    private StudenteDto studente;
}
