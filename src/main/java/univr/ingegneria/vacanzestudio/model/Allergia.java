package univr.ingegneria.vacanzestudio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "allergia")
public class Allergia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_allergia", nullable = false, updatable = false)
    private Long id;

    private String nomeAllergene;

    private String descrizionePrecauzione;
}
