package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VacanzaTerminataDto {
    private Long id;

    private String cittaDiPermanenza;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    private Double votoMedioQuestionari;

    private List<QuestionarioDto> questionarioDtoList;
}
