package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Vacanza vacanza;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_famiglia")
    @JsonIgnore
    private Famiglia famiglia;
}
