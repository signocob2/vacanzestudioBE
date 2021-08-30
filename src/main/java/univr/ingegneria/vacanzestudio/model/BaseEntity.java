package univr.ingegneria.vacanzestudio.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    private LocalDateTime timestamp_inserimento = LocalDateTime.now();

    private String utente_inserimento;

    private LocalDateTime timestamp_modifica = LocalDateTime.now();

    private String utente_modifica;
}
