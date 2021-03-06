package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrenotazioneVacanzaFamigliaDto {
    private Long id;

    private Long idVacanza;

    private Long idUtente;

    private String nomeAmico;

    private String emailAmico;
    
    private String pagamentoCartaBonifico;
}
