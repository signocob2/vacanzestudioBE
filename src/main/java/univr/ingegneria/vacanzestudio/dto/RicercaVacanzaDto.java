package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RicercaVacanzaDto {
    private Long id;

    private String cittaDiPermanenza;

    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;
}
