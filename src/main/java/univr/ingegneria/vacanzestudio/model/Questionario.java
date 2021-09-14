package univr.ingegneria.vacanzestudio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "questionario")
public class Questionario extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questionario", nullable = false, updatable = false)
    private Long id;

    private String esperienzaPositiva;

    private String alloggioCurato;

    private String personaleDisponibile;

    private String utilePerLingua;

    private String prezzoGiteAppropriato;

    private String votoGradimento;

    private String commentoLibero;

    private String isCompilato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private Vacanza vacanza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente")
    private Utente utente;
}
