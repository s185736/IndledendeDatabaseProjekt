import java.sql.*;
import java.util.Scanner;

public class ManipulateUniversity {
    public static void main(String[] args) {

        // Tilpas variable til jeres database.
        // -----------------------------------
        String host = "localhost"; //host is "localhost" or "127.0.0.1"
        String port = "3306"; //port is where to communicate with the RDBM system
        String database = "tidsmaskine_DB"; //database containing tables to be queried
        String cp = "utf8"; //Database codepage supporting danish (i.e. æøåÆØÅ)

        String csvFile = "tilmeldinger.csv";


        // Set username og password.
        // -------------------------
        String username = "root";		// Username for connection
        String password = "02327";	// Password for username
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;

        try {
            Scanner scanner = new Scanner(System.in, "CP850"); //Danish Console CodePage
            System.out.println("Type sql manipulation: ");
            String sqlManipulation = scanner.nextLine();
            scanner.close();

            // Get a connection.
            // -----------------
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create and execute Update.
            // --------------------------
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);

            // Close connection.
            // -----------------
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}