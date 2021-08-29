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
@Table(name = "attivita_college")
public class AttivitaCollege extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_attivita_college", nullable = false, updatable = false)
    private Long id;

    private String nomeAttivita;

    private String descrizioneAttivita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_college")
    private College college;
}
