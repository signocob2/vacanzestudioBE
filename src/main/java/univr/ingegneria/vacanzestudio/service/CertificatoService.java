package univr.ingegneria.vacanzestudio.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import univr.ingegneria.vacanzestudio.dao.CertificatoDao;
import univr.ingegneria.vacanzestudio.model.Certificato;
import univr.ingegneria.vacanzestudio.model.Vacanza;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CertificatoService {
    @Resource
    CertificatoDao certificatoDao;

    public Certificato findCertificatoById(Long idCertificato) {
        return certificatoDao.findById(idCertificato).orElse(null);
    }

    public List<Certificato> findAllCertificatoByUtenteId(Long idUtente) {
        return certificatoDao.findCertificatoByUtenteId(idUtente);
    }

    public Certificato findCertificatoByUtenteIdAndVacanzaId(Long idUtente, Long idVacanza) {
        return certificatoDao.findCertificatoByUtenteIdAndVacanzaId(idUtente, idVacanza).orElse(null);
    }

    public FileInputStream createCertificatoPdf(Long idCertificato) throws DocumentException, URISyntaxException, IOException {
        Certificato certificato = this.findCertificatoById(idCertificato);

        Document document = new Document(PageSize.A4, 2, 2, 15, 2);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/certificato.pdf"));

        document.open();

        // Inserimento immagine
        Path path = Paths.get(ClassLoader.getSystemResource("univr_logo.jfif").toURI());
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        img.scalePercent(40, 40);
        float x = ((PageSize.A4.getWidth()) / 2) - 40;
        float y = ((PageSize.A4.getHeight()) - 100);
        img.setAbsolutePosition(x, y);
        document.add(img);

        // Inserimento tabella
        PdfPTable table = new PdfPTable(4);
        addTableHeader(table);
        addTableRows(table, certificato);
        table.setTotalWidth(450f);
        table.writeSelectedRows(0, -1, 75, 700, writer.getDirectContent());

        document.close();

        return new FileInputStream("src/main/resources/certificato.pdf");
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Codice vacanza", "Città di permanenza", "Lingua Straniera studiata", "Livello raggiunto")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addTableRows(PdfPTable table, Certificato certificato) {
        Vacanza vacanza = certificato.getVacanza();
        table.addCell(vacanza.getId().toString());
        table.addCell(vacanza.getCittaDiPermanenza());
        table.addCell(vacanza.getLinguaStranieraStudiata());
        table.addCell(certificato.getLivelloRaggiunto());
    }
}
