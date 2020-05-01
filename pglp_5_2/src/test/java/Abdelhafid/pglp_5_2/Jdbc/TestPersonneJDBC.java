package Abdelhafid.pglp_5_2.Jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.Telephone;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class TestPersonneJDBC {

	private PersonnelDaoJdbc p1, p;
	private Personnel pCreate, personne;

	@Test()
	public void testCreate() {

		Personnel personne4 = new PersonnelBuilder("BELHABib", "riyad", "infor")
				.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		p = new PersonnelDaoJdbc();

		pCreate = p.Create(personne4);

		assertEquals(pCreate.getId(), personne4.getId());

		assertTrue(pCreate.getNom().equals(personne4.getNom()) && pCreate.getPrenom().equals(personne4.getPrenom())
				&& pCreate.getDate_naissance().isEqual(personne4.getDate_naissance()));

	}

	@Test
	public void Find() {

		Personnel pCreate1, pRead;
		PersonnelDaoJdbc p1;

		Personnel personne2 = new PersonnelBuilder("BELHABibbb", "riyadgg", "inforff")
				.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		p1 = new PersonnelDaoJdbc();
		pCreate1 = p1.Create(personne2);
		pRead = p1.Find(1);

		//String nom = "BELHABibbb";

		assertEquals(pCreate1.getNom(), pRead.getNom());

	}
@Test
	public void deleteTest() {
	Personnel pCreate2;
	PersonnelDaoJdbc p3;

	Personnel personne3 = new PersonnelBuilder("BELHABibbb", "abdelhafid", "informaticien")
			.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
			.ajouter_numero(new Telephone("Perso", "065078554512")).ajouter_numero(new Telephone("Pro", "0650784513"))
			.build();

	p3 = new PersonnelDaoJdbc();
	pCreate2 = p3.Create(personne3);
	p3.delete(personne3);
	
}
}
