package univr.ingegneria.vacanzestudio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacanza")
public class Vacanza extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vacanza", nullable = false, updatable = false)
    private Long id;

    private String cittaDiPermanenza;

    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacanza")
    private List<Gita> gitaList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private VacanzaCollege vacanzaCollege;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private VacanzaFamiglia vacanzaFamiglia;
}
