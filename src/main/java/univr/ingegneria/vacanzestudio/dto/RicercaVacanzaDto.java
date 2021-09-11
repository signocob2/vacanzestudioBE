package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RicercaVacanzaDto {
    private Long id;

    private String cittaDiPermanenza;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;
}
