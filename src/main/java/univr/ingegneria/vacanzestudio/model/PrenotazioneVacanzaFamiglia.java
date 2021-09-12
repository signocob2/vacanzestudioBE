package univr.ingegneria.vacanzestudio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "prenotazione_vacanza_famiglia")
public class PrenotazioneVacanzaFamiglia extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione_vacanza_famiglia", nullable = false, updatable = false)
    private Long id;

    private String nomeAmico;

    private String emailAmico;

    private String pagamentoCartaBonifico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private Vacanza vacanza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utente")
    private Utente utente;
}
