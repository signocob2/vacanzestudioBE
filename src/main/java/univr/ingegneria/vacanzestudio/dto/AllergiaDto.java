package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllergiaDto extends BaseDto {
    private Long id;

    @JsonIgnore
    private StudenteDto studente;

    private String nomeAllergene;

    private String descrizionePrecauzioniNecessarie;
}
