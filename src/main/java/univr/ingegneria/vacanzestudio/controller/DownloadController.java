package univr.ingegneria.vacanzestudio.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univr.ingegneria.vacanzestudio.service.CertificatoService;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/download")
class DownloadController {

    @Resource
    CertificatoService certificatoService;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable("id") Long idCertificato) throws IOException, DocumentException, URISyntaxException {
        FileInputStream pdf = certificatoService.createCertificatoPdf(idCertificato);

        return ResponseEntity.ok().contentLength(pdf.available())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdf));
    }
}