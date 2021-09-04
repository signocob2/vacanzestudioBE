package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;
import univr.ingegneria.vacanzestudio.model.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VacanzaDto extends BaseDto {
    private Long id;

    private String cittaDiPermanenza;

    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    private List<GitaDto> gitaList;

    private PrenotazioneVacanzaCollegeDto prenotazioneVacanzaCollege;

    private PrenotazioneVacanzaFamigliaDto prenotazioneVacanzaFamiglia;

    private CollegeDto college;

    private FamigliaDto famiglia;
}
