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
@Table(name = "utente")
public class Utente extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utente", nullable = false, updatable = false)
    private Long id;

    private String nome;

    private String cognome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDiNascita;

    private String comuneDiNascita;

    private String provinciaDiNascita;

    private String codiceFiscale;

    private String indirizzo;

    private String recapitoTelefonico;

    private String email;

    private String password;

    private String isAdmin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Allergia> allergiaList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Hobby> hobbyList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Genitore> genitoreList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<PrenotazioneVacanzaCollege> prenotazioneVacanzaCollegeList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<PrenotazioneVacanzaFamiglia> prenotazioneVacanzaFamigliaList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Questionario> questionarioList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    private List<Certificato> certificatoList;
}
