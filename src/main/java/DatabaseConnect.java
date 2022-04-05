import java.sql.*;


/*
* Bruges ikke....
* */

public class DatabaseConnect {

    final String host = "localhost"; //host is "localhost" or "127.0.0.1"
    final int port = 3306; //port is where to communicate with the RDBM system
    final String database = "tidsmaskine_DB"; //database containing tables to be queried
    String cp = "utf8"; //Database codepage supporting danish (i.e. æøåÆØÅ)

    // Set username og password.
    // -------------------------
    final String username = "root";		// Username for connection
    final String password = "02327";	// Password for username

    Connection connection;
    String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;

    DatabaseConnect() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}