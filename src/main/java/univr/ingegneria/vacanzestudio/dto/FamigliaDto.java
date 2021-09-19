package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class FamigliaDto {
    private Long id;

    private String nomeCapoFamiglia;

    private String cognomeCapoFamiglia;

    private String recapitoTelefonicoCapoFamiglia;

    private Integer numeroComponenti;

    private String presenzaAnimaliDomestici;

    private Integer numeroCamereDisponibili;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer numeroCamereRimanenti;

    private Integer numeroBagni;

    private BigDecimal distanzaCentroCittaKm;
}
