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

	private PersonnelDaoJdbc p;
	private Personnel pCreate;

	@Test()
	public void testCreate() {

		Personnel personne= new PersonnelBuilder("BELHABib", "riyad", "infor")
				.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		p = new PersonnelDaoJdbc();

		pCreate = p.Create(personne);

		assertEquals(pCreate.getId(), personne.getId());

		assertTrue(pCreate.getNom().equals(personne.getNom()) && pCreate.getPrenom().equals(personne.getPrenom())
				&& pCreate.getDate_naissance().isEqual(personne.getDate_naissance()));

	}

	@Test
	public void Find() {

		Personnel pCreate1, pRead;
		PersonnelDaoJdbc p1;

		Personnel personne8= new PersonnelBuilder("BELHABibbb", "riyaddd", "inforff")
				.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		p1 = new PersonnelDaoJdbc();
		pCreate1 = p1.Create(personne8);
		pRead = p1.Find(pCreate1.getId());

	

		assertEquals(pCreate1.getNom(), pRead.getNom());

	}
@Test
	public void deleteTest() {
	PersonnelDaoJdbc p3;
	Personnel perss;

	Personnel personne3 = new PersonnelBuilder("BELHABibbb", "abdelhafid", "informaticien")
			.Naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE))
			.ajouter_numero(new Telephone("Perso", "065078554512")).ajouter_numero(new Telephone("Pro", "0650784513"))
			.build();

	p3 = new PersonnelDaoJdbc();
	perss= p3.Create(personne3);
	
	p3.delete(personne3);
	
}

@Test
public void UpdateTest() throws SQLException {
	Personnel pCreateUp;
	PersonnelDaoJdbc pUp = null;
	
	Personnel pers = new PersonnelBuilder("BELHABIB", "ABDELHAFID", "ANIMATEUR")
			.Naissance(LocalDate.parse("1998-07-09", DateTimeFormatter.ISO_DATE))
			.ajouter_numero(new Telephone("Perso", "065078554512")).ajouter_numero(new Telephone("Pro", "0650784513"))
			.build();

	pUp=new PersonnelDaoJdbc();
	
	 pCreateUp = pUp.Create(pers);
	
	 
	pers.setNom("BEL");
	pers.setPrenom("ABDEL");
	pers.setFonction("vendeur");
    pers.setDate_naissance(LocalDate.parse("1994-07-09", DateTimeFormatter.ISO_DATE));
	
	 
	
	 
	 pCreateUp = pUp.Update(pers);
	 assertEquals( pCreateUp.getNom(),"BEL");
	 assertEquals( pCreateUp.getPrenom(),"ABDEL");
		assertEquals(pCreateUp.getFonction(),"vendeur");
		assertEquals(pCreateUp.getDate_naissance().toString(),"1994-07-09");
	
	
	Connection connect = DriverManager.getConnection(Initializer.url);
	PreparedStatement stmt = connect.prepareStatement("SELECT * FROM PERSONNE");

	ResultSet resultat1 = stmt.executeQuery();

	System.out.println("---Table Personne Apres Modification:---\n");
	System.out.println("id\t nom\t prenom\t fonction\t Naissance");

	while (resultat1.next()) {
		System.out.printf("%d\t%s\t%s\t%s\t%s%n", resultat1.getInt("id"), resultat1.getString("nom"),
				resultat1.getString("prenom"), resultat1.getString("fonction"),
				resultat1.getString("DateNaisssance"));
	}
	System.out.println("---------------------" + "---------------\n");

	resultat1.close();
	
	
}









}
