package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genitore")
public class Genitore extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genitore", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Studente studente;

    private String nome;

    private String cognome;

    private String recapitoTelefonico;

    private String email;
}
