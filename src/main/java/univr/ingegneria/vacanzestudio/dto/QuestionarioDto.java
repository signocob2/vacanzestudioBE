package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionarioDto {
    private Long id;

    private Long idUtente;

    private Long idVacanza;

    private String esperienzaPositiva;

    private String alloggioCurato;

    private String personaleDisponibile;

    private String utilePerLingua;

    private String prezzoGiteAppropriato;

    private String votoGradimento;

    private String commentoLibero;

    @JsonIgnore
    private String isCompilato;
}
