import java.io.IOException;
import java.sql.Statement;
import java.util.List;

public class IndlaesDatafilEksempel {

	public static void CSVimport() {

		IndlaesPersonerOgTilmeldinger laeser = new IndlaesPersonerOgTilmeldinger();
		try {

			ManipulateUniversity db = new ManipulateUniversity();
			db.CSVFile();
			/*
			db.ActionDatabase();


			List<PersonOgTilmelding> personerOgTilmeldinger = laeser.indlaesPersonerOgTilmeldinger(args[0]);
			for(PersonOgTilmelding personOgTilmelding : personerOgTilmeldinger) {
				System.out.print("Person: " +personOgTilmelding.getPerson());
				if(personOgTilmelding.getTilmelding() != null)
					System.out.println("\tTilmelding: " +personOgTilmelding.getTilmelding());

				else
					System.out.println("\t Ingen tilhørende tilmelding");
			}
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sqlManipulation() {
		String[] args = {};
		main(args);
	}

	public static void main(String[] args) {
		IndlaesPersonerOgTilmeldinger laeser = new IndlaesPersonerOgTilmeldinger();
		try {
			List<PersonOgTilmelding> personerOgTilmeldinger = laeser.indlaesPersonerOgTilmeldinger(args[0]);
			for(PersonOgTilmelding personOgTilmelding : personerOgTilmeldinger) {
				System.out.print("Person: " +personOgTilmelding.getPerson());
				if(personOgTilmelding.getTilmelding() != null)
					System.out.println("\tTilmelding: " +personOgTilmelding.getTilmelding());
				else
					System.out.println("\t Ingen tilhørende tilmelding");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}



