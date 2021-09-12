package univr.ingegneria.vacanzestudio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificatoDto {
    private Long id;

    private Long idVacanza;

    private String cittaDiPermanenza;

    private String linguaStranieraStudiata;

    private String livelloRaggiunto;
}
