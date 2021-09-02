package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "famiglia")
public class Famiglia extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_famiglia", nullable = false, updatable = false)
    private Long id;

    private String nomeCapoFamiglia;

    private String cognomeCapoFamiglia;

    private String recapitoTelefonicoCapoFamiglia;

    private Integer numeroComponenti;

    private String presenzaAnimaliDomestici;

    private Integer numeroCamereDisponibili;

    private Integer numeroBagni;

    private BigDecimal distanzaCentroCittaKm;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "famiglia")
    @JsonIgnore
    private List<Vacanza> vacanzaList;

    public boolean isPresentiAnimaliDomestici() {
        return StringUtils.equals(this.presenzaAnimaliDomestici, "S");
    }
}
