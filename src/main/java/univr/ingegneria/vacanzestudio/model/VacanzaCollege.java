package univr.ingegneria.vacanzestudio.model;

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
@Table(name = "vacanza_college")
public class VacanzaCollege extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_vacanza_college", nullable = false, updatable = false)
    private Long id;

    private String singolaCondivisa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacanza")
    private Vacanza vacanza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_college")
    private College college;

    public boolean isSingola() {
        return StringUtils.equals(this.singolaCondivisa, "S");
    }

    public boolean isCondivisa() {
        return !this.isSingola();
    }
}
