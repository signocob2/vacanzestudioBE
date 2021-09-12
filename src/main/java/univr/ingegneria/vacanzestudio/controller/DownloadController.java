package univr.ingegneria.vacanzestudio.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.service.CertificatoService;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/download")
class DownloadController {

    @Resource
    CertificatoService certificatoService;

    @GetMapping("/pdf/{id}")
    public ResponseEntity<InputStreamResource> getPdf(@PathVariable("id") Long idCertificato) throws FileNotFoundException, DocumentException {
        Certificato c = certificatoService.findCertificatoById(idCertificato);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/certificato.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        document.add(new Chunk(System.lineSeparator() + "Codice vacanza: " + c.getVacanza().getId(), font));
        document.add(new Chunk(System.lineSeparator() + "Città di permanenza: " + c.getVacanza().getCittaDiPermanenza(), font));
        document.add(new Chunk(System.lineSeparator() + "Lingua Straniera studiata: " + c.getVacanza().getLinguaStranieraStudiata(), font));
        document.add(new Chunk(System.lineSeparator() + "Livello raggiunto:" + c.getLivelloRaggiunto(), font));

        document.close();

        FileInputStream is = new FileInputStream("src/main/resources/certificato.pdf");
        long r = 0;

        try {
            r = is.available();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().contentLength(r)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(is));

    }
}