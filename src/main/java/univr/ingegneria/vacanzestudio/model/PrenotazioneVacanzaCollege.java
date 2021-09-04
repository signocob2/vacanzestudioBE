package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "prenotazione_vacanza_college")
public class PrenotazioneVacanzaCollege extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione_vacanza_college", nullable = false, updatable = false)
    private Long id;

    private String singolaCondivisa;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Vacanza vacanza;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Studente studente;

    public boolean isSingola() {
        return StringUtils.equals(this.singolaCondivisa, "S");
    }

    public boolean isCondivisa() {
        return !this.isSingola();
    }
}
