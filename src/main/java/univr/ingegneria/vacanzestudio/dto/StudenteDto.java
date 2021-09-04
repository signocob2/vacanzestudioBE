package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StudenteDto extends BaseDto {
    private Long id;

    private String nome;

    private String cognome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiNascita;

    private String comuneDiNascita;

    private String provinciaDiNascita;

    private String codiceFiscale;

    private String indirizzo;

    private String recapitoTelefonico;

    private String email;

    private String password;

    private List<AllergiaDto> allergiaList;

    private List<HobbyDto> hobbyList;

    private List<GenitoreDto> genitoreList;

    private List<PrenotazioneVacanzaCollegeDto> prenotazioneVacanzaCollegeList;

    private List<PrenotazioneVacanzaFamigliaDto> prenotazioneVacanzaFamigliaList;
}
