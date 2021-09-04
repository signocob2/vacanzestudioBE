package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;
import univr.ingegneria.vacanzestudio.model.BaseEntity;
import univr.ingegneria.vacanzestudio.model.Studente;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "allergia")
public class AllergiaDto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_allergia", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_studente")
    private Studente studente;

    private String nomeAllergene;

    private String descrizionePrecauzioniNecessarie;
}
