package univr.ingegneria.vacanzestudio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HobbyDto extends BaseDto {
    private Long id;

    private StudenteDto studente;

    private String nome;
}
