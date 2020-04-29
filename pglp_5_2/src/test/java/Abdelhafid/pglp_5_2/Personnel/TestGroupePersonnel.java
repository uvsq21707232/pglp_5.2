package Abdelhafid.pglp_5_2.Personnel;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class TestGroupePersonnel {

	@Test
	/* tester le constructeur */
	public void constructeur() {
		GroupePersonnel grp = new GroupePersonnel();

		Iterator<PersonnelInterface> ip = grp.iterator();
		assertFalse(ip.hasNext());
	}

	@Test
	/* tester la méthode Ajouter */
	public void testAjouter() {
		GroupePersonnel cp = new GroupePersonnel();
		cp.ajouter(new GroupePersonnel());
		Iterator<PersonnelInterface> inter_per = cp.iterator();

		assertTrue(inter_per.hasNext());
	}

	@Test
	/* tester la méthode supprimer */
	public void testSupprimer() {

		GroupePersonnel gp1 = new GroupePersonnel();
		GroupePersonnel gp2 = new GroupePersonnel();

		gp1.ajouter(gp2);
		gp1.supprimer(gp2);

		Iterator<PersonnelInterface> inter_per = gp1.iterator();
		assertFalse(inter_per.hasNext());
	}

	@Test
	/* tester la méthode supprimer */
	public void testSerialiser() {

		GroupePersonnel gp = new GroupePersonnel();

		Personnel personne = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		gp.ajouter(personne);

		gp.serialiser("groupe");

		GroupePersonnel gp2 = GroupePersonnel.deSerialiser("groupe");
		File f = new File("groupe");
		f.delete();

		assertEquals(gp.getId_composite(), gp2.getId_composite());

	}
}
