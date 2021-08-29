package univr.ingegneria.vacanzestudio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "college")
public class College extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_college", nullable = false, updatable = false)
    private Long id;

    private String nome;

    private String indirizzo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "college")
    private List<AttivitaCollege> attivitaCollegeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "college")
    private List<VacanzaCollege> vacanzaCollegeList;
}
