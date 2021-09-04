package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrenotazioneVacanzaFamigliaDto extends BaseDto {
    private Long id;

    private String nomeAmico;

    private String cognomeAmico;

    private String emailAmico;

    @JsonIgnore
    private VacanzaDto vacanza;

    @JsonIgnore
    private StudenteDto studente;
}
