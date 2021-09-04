package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenitoreDto extends BaseDto {
    private Long id;

    private StudenteDto studente;

    private String nome;

    private String cognome;

    private String recapitoTelefonico;

    private String email;
}
