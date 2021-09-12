package univr.ingegneria.vacanzestudio.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/download")
class DownloadController {

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> getPdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/certificato.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Hello Worlddddhygyud", font);

        document.add(chunk);
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