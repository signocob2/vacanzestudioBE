package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrenotazioneVacanzaCollegeDto {
    private Long id;

    private Long idVacanza;

    private Long idUtente;

    private String singolaCondivisa;

    private String pagamentoCartaBonifico;
}
