package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class FamigliaDto extends BaseDto {
    private Long id;

    private String nomeCapoFamiglia;

    private String cognomeCapoFamiglia;

    private String recapitoTelefonicoCapoFamiglia;

    private Integer numeroComponenti;

    private String presenzaAnimaliDomestici;

    private Integer numeroCamereDisponibili;

    private Integer numeroBagni;

    private BigDecimal distanzaCentroCittaKm;

    @JsonIgnore
    private List<VacanzaDto> vacanzaList;
}
