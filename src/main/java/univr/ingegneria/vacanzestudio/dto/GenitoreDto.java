package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenitoreDto {
    private Long id;

    private String nome;

    private String cognome;

    private String recapitoTelefonico;

    private String email;
}
