package univr.ingegneria.vacanzestudio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HobbyDto extends BaseDto {
    private Long id;

    @JsonIgnore
    private StudenteDto studente;

    private String nome;
}
