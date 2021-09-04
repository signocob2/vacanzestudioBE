package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String cognomeAmico;

    private String emailAmico;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Vacanza vacanza;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Studente studente;
}
