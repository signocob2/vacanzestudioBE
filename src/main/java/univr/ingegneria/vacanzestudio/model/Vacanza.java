package univr.ingegneria.vacanzestudio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vacanza")
public class Vacanza extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacanza", nullable = false, updatable = false)
    private Long id;

    private String cittaDiPermanenza;

    private LocalDate dataDiPartenza;

    private Integer numeroDiSettimaneDurata;

    private String linguaStranieraStudiata;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vacanza")
    private List<Gita> gitaList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vacanza")
    private List<PrenotazioneVacanzaCollege> prenotazioneVacanzaCollegeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vacanza")
    private List<PrenotazioneVacanzaFamiglia> prenotazioneVacanzaFamigliaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_college")
    private College college;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_famiglia")
    private Famiglia famiglia;
}
