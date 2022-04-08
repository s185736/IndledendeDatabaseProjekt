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
        String password = "";	// Password for username
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=" + cp;

        /*Husk, at ændre dette...*/
        String csvFile = "./src/tilmeldinger.csv";


            int size = 20;
            Connection connection = null;

            try{
                connection = DriverManager.getConnection(url,username,password);
                connection.setAutoCommit(false);
                String q = "SELECT * FROM person";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(q);
                String sql_command = "insert into person(email, fornavn, efternavn, adresse, koen, foedselsdato) values(?,?,?,?,?,?)";
                //String sql_command2 = "insert into personTilmelding(email, fornavn, efternavn, koen, foedselsdato, foreningsId, eventTypeId, eventDato) values(?,?,?,?,?,?,?,?)";
                //String sql_command2 = "insert into foreningtilmelding(foreningsId, eventTypeId, eventDato) values(?,?,?)";
                PreparedStatement prepstat = connection.prepareStatement(sql_command);
                //PreparedStatement prepstat2 = connection.prepareStatement(sql_command2);
                BufferedReader bufread = new BufferedReader(new FileReader(csvFile));

                String lineText = null;
                int count = 0;

                /**start af select: tjekke hvor mange rækker der er..**/
                /*Herunder har vi implementeret hvordan vi tjekker hvor mange rækker der er i databasen.*/
                /*String strS = "Select * From projekt.personID;"; //skal lige tjekkes From, hvor den får data fra navnet på det i MySQL.
                System.out.println("SQL statement er: "+strS+"\n");
                ResultSet resultS = st.executeQuery(strS);
                while (resultS.next()){
                    ++count;
                }*/

                bufread.readLine();
                while ((lineText=bufread.readLine())!=null){
                    String[] data=lineText.split(",");
                    String[] row=data[0].split(";");
                    String email = row[0];
                    String[] navn=row[1].split(" ");
                    String fornavn = navn[0];
                    String efternavn = navn[1];
                    String adresse = row[2];
                    String koen = row[3];
                    String foedselsdato = row[4];
                    // For personTilmelding
                    /*
                    String foreningsId = row[5];
                    String eventTypeId = row[6];
                    String eventDato = row[7];

                     */


                    //int foedselsdato = Integer.parseInt(data[1]);


                    prepstat.setString(1,email);
                    prepstat.setString(2,fornavn);
                    prepstat.setString(3,efternavn);
                    prepstat.setString(4,adresse);
                    prepstat.setString(5,koen);
                    prepstat.setInt(6, Integer.parseInt(foedselsdato));

                    prepstat.addBatch();

                    /*
                    //prepstat2.setString(1,email);
                    //prepstat2.setString(2,fornavn);
                    //prepstat2.setString(3,efternavn);
                    //prepstat2.setString(4,koen);
                    //prepstat2.setInt(5, Integer.parseInt(foedselsdato));

                    prepstat2.setString(1,foreningsId);
                    prepstat2.setString(2,eventTypeId);
                    prepstat2.setInt(3,Integer.parseInt(eventDato));
                    prepstat2.addBatch();

                     */

                    if (count % size == 0){
                        prepstat.executeBatch();
                        //prepstat2.executeBatch();
                    }
                }
                /*lukker alt ned.*/
                bufread.close();
                prepstat.executeBatch();
               // prepstat2.executeBatch();
                connection.commit();
                connection.close();
                System.out.println("Data er blevet importeret...");

            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }

    public static void ActionDatabase() {
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