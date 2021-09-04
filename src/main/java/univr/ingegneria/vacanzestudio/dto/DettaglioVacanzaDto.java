package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DettaglioVacanzaDto {
    private Long id;

    private String cittaDiPermanenza;

    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    private List<GitaDto> gitaList;

    private CollegeDto college;

    private FamigliaDto famiglia;
}
