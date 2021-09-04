package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "attivita_college")
public class AttivitaCollege extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attivita_college", nullable = false, updatable = false)
    private Long id;

    private String nomeAttivita;

    private String descrizioneAttivita;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_college")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private College college;
}
