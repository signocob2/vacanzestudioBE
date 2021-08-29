package univr.ingegneria.vacanzestudio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacanza_famiglia")
public class VacanzaFamiglia extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vacanza_famiglia", nullable = false, updatable = false)
    private Long id;

    private String nomeAmico;

    private String cognomeAmico;

    private String emailAmico;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private Vacanza vacanza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_famiglia")
    private Famiglia famiglia;
}
