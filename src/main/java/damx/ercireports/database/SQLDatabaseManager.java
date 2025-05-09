package damx.ercireports.database;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseManager {
    private static final Dotenv dotenv = Dotenv.load();

    private static final int DB_PORT = Integer.parseInt(dotenv.get("DB_PORT"));
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    private static final String DB_NAME = dotenv.get("DB_NAME");
    /**
     * Establece una conexión a la base de datos PostgreSQL
     *
     * @return Una referencia a la conexión a la base de datos.
     * @throws SQLException Si ocurre un error durante la conexión.
     */
    public static Connection connect() throws SQLException {
        try {
            // Establecer la conexión a la base de datos PostgreSQL
            String jdbcUrl = "jdbc:postgresql://localhost:" + DB_PORT + "/" + DB_NAME;
            Connection connection = DriverManager.getConnection(jdbcUrl, DB_USER, DB_PASSWORD);

            if (connection == null) {
                System.out.println("No se pudo conectar a la base de datos PostgreSQL. Asegúrate de que la URL y las credenciales sean correctas.");
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos PostgreSQL.");
        }
    }

    /**
     * Cierra la conexión a la base de datos PostgreSQL y la sesión SSH.
     *
     * @param connection La conexión que se debe cerrar.
     * @throws SQLException Si ocurre un error durante la desconexión.
     */
    public static void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
