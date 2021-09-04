package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "college")
public class College extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_college", nullable = false, updatable = false)
    private Long id;

    private String nome;

    private String indirizzo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "college")
    private List<AttivitaCollege> attivitaCollegeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "college")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Vacanza> vacanzaList;
}
