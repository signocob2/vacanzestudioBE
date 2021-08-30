package univr.ingegneria.vacanzestudio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "studente")
public class Studente extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_studente", nullable = false, updatable = false)
    private Long id;

    private String nome;

    private String cognome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataDiNascita;

    private String comuneDiNascita;

    private String provinciaDiNascita;

    private String codiceFiscale;

    private String indirizzo;

    private String recapitoTelefonico;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studente")
    private List<Allergia> allergiaList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studente")
    private List<Hobby> hobbyList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studente")
    private List<Genitore> genitoreList;
}
