package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenitoreDto extends BaseDto {
    private Long id;

    @JsonIgnore
    private StudenteDto studente;

    private String nome;

    private String cognome;

    private String recapitoTelefonico;

    private String email;
}
