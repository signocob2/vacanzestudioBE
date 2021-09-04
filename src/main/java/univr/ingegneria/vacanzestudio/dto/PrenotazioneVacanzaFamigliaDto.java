package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrenotazioneVacanzaFamigliaDto {
    private Long id;

    private String nomeAmico;

    private String cognomeAmico;

    private String emailAmico;
}
