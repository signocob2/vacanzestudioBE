package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import univr.ingegneria.vacanzestudio.model.BaseEntity;
import univr.ingegneria.vacanzestudio.model.College;

@Getter
@Setter
public class AttivitaCollegeDto extends BaseDto {
    private Long id;

    private String nomeAttivita;

    private String descrizioneAttivita;

    @JsonIgnore
    private College college;
}
