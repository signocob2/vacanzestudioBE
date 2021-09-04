package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import univr.ingegneria.vacanzestudio.model.Gita;

import java.math.BigDecimal;

@Getter
@Setter
public class GitaDto extends BaseDto {
    private Long id;

    @JsonIgnore
    private VacanzaDto vacanza;

    private String destinazione;

    private BigDecimal costoEuro;

    private Integer numeroOre;

    private String descrizione;
}
