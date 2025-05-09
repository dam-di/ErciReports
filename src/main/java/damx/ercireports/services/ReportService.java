package damx.ercireports.services;

import damx.ercireports.database.SQLDatabaseManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class ReportService {

    private Connection connection;

    private boolean initDBConnection(){
        try {
            connection = SQLDatabaseManager.connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos");
        }
        return false;
    }

    private boolean closeDBConnection(){
        try {
            SQLDatabaseManager.disconnect(connection);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al desconectar con la base de datos");
        }
        return false;
    }

    public byte[] generarReport(String reportName, String correo) throws Exception {
       // openSshTunnel("192.168.88.46", 12000, "sshuser-server", "ssh-password", 5432, "remote-db-host", 5432);

        if (!initDBConnection()) {
            throw new SQLException("Error al conectar con la base de datos");
        }

        InputStream reportStream = new FileInputStream("src/main/resources/reports/" + reportName + ".jasper");



        if (reportStream == null) {
            throw new JRException("El informe no se encontró: " + reportName);
        }

        Map<String, Object> parms = new HashMap<>();
        // Puedes agregar parámetros aquí si es necesario
        //parms.put("email_filter", correo);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parms, connection);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
