package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GitaDto {
    private Long id;

    private String destinazione;

    private BigDecimal costoEuro;

    private Integer numeroOre;

    private String descrizione;
}
