package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionarioDto {
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
