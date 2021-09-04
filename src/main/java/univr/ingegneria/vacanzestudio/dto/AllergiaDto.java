package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllergiaDto {
    private Long id;

    private String nomeAllergene;

    private String descrizionePrecauzioniNecessarie;
}
