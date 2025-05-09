package damx.ercireports.controllers;

import damx.ercireports.services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/report")

public class ReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getReport/{correo}")
    public ResponseEntity<byte[]> getEvents(@PathVariable String correo){
        System.out.println("Obteniendo informe" +correo);

        try {
            byte[] report = reportService.generarReport("Blank_A4", correo);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.add("Content-Disposition","inline; filename=report.pdf");

            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (JRException e) {
            System.out.println(e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
