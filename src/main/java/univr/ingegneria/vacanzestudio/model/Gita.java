package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gita")
public class Gita extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gita", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Vacanza vacanza;

    private String destinazione;

    private BigDecimal costoEuro;

    private Integer numeroOre;

    private String descrizione;
}
