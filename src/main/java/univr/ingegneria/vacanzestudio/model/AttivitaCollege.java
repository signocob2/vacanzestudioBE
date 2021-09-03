package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
