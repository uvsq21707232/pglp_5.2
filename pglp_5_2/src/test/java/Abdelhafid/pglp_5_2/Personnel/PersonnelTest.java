package Abdelhafid.pglp_5_2.Personnel;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.Telephone;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class PersonnelTest {

	@Test()

	public void test() {

		Personnel personne = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();
		personne.print();

	}

	@Test()

	public void testSerialiser() {

		Personnel personne1 = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();
		personne1.serialiser("personne1");

		Personnel personne2 = Personnel.deSerialiser("personne1");
		File file = new File("personne1");
		file.delete();
		assertTrue(personne1.getNom().equalsIgnoreCase(personne2.getNom())
				&& personne1.getPrenom().equals(personne2.getPrenom())
				&& personne1.getDate_naissance().isEqual(personne2.getDate_naissance())

		);

	}

}
