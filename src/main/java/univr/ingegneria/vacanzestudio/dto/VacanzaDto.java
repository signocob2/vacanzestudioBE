package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VacanzaDto {
    private Long id;

    private String cittaDiPermanenza;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    private List<GitaDto> gitaList;

    private PrenotazioneVacanzaCollegeDto prenotazioneVacanzaCollege;

    private PrenotazioneVacanzaFamigliaDto prenotazioneVacanzaFamiglia;

    private CollegeDto college;

    private FamigliaDto famiglia;
}
