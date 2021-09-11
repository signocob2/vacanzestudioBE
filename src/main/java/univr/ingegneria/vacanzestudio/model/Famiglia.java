package univr.ingegneria.vacanzestudio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
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
    private List<Vacanza> vacanzaList;
}
