import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Integer.parseInt;

public class ManipulateUniversity {


    public void CSVFile() {
        // Tilpas variable til jeres database.
        // -----------------------------------
        String host = "localhost"; //host is "localhost" or "127.0.0.1"
        String port = "3306"; //port is where to communicate with the RDBM system
        String database = "tidsmaskine_DB"; //database containing tables to be queried
        String cp = "utf8"; //Database codepage supporting danish (i.e. æøåÆØÅ)

        String username = "root";		// Username for connection
        String password = "02327";	// Password for username
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;

        /*Husk, at ændre dette...*/
        String csvFile = "C:\\Users\\Privat\\Desktop\\tilmaeldinger.csv";

            int size = 20;
            Connection connection = null;

            try{
                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);

                String sql_command = "insert into person(email, fornavn, efternavn, koen, foedselsdato) values(?,?,?,?,?)";

                PreparedStatement prepstat = connection.prepareStatement(sql_command);
                BufferedReader bufread = new BufferedReader(new FileReader(csvFile));

                String lineText = null;
                int count=0;

                bufread.readLine();
                while ((lineText=bufread.readLine())!=null){
                    String[] data=lineText.split(",");

                    String email = data[0];
                    String fornavn = data[0];
                    String efternavn = data[1];
                    String koen = data[1];
                    String foedselsdato = data[1];
                    //int foedselsdato = Integer.parseInt(data[1]);

                    prepstat.setString(1,email);
                    prepstat.setString(2,fornavn);
                    prepstat.setString(2,efternavn);
                    prepstat.setString(2,koen);
                    prepstat.setInt(2, Integer.parseInt(foedselsdato));

                    prepstat.addBatch();

                    if (count % size == 0){
                        prepstat.executeBatch();
                    }
                }
                /*lukker alt ned.*/
                bufread.close();
                prepstat.executeBatch();
                connection.commit();
                connection.close();
                System.out.println("Data er blevet importeret...");

            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }

    public void ActionDatabase() {
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