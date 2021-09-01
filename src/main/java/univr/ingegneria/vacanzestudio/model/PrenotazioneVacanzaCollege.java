package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private Vacanza vacanza;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente")
    @JsonIgnore
    private Studente studente;

    public boolean isSingola() {
        return StringUtils.equals(this.singolaCondivisa, "S");
    }

    public boolean isCondivisa() {
        return !this.isSingola();
    }
}
