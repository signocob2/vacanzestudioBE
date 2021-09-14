package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class VacanzaPrenotataDto {
    private Long id;

    private String cittaDiPermanenza;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    private String singolaCondivisa;

    private String nomeAmico;

    private String emailAmico;

    private String pagamentoCartaBonifico;

    private String dettagliAggiuntivi;

    private Boolean isVacanzaTerminata;

    private Boolean isQuestionarioCompilato;
}
