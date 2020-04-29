package Abdelhafid.pglp_5_2.Dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.AffichageParGroupe;
import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.Telephone;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class AffichageParGroupeDaoTest {

	@Test
	public void testAjout() {

		AffichageParGroupeDAO affDao = new AffichageParGroupeDAO();

		GroupePersonnel g1 = new GroupePersonnel();
		GroupePersonnel g2 = new GroupePersonnel();
		GroupePersonnel g3 = new GroupePersonnel();

		Personnel personne1 = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		Personnel personne2 = new PersonnelBuilder("BELHABIB", "Riyad", "Secretaire")
				.Naissance(LocalDate.parse("1992-08-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Personnel", "0650784512"))
				.ajouter_numero(new Telephone("Pro", "0650784513")).build();

		AffichageParGroupe affpg = new AffichageParGroupe();

		g1.ajouter(personne1);
		g2.ajouter(personne2);
		g1.ajouter(g2);
		g2.ajouter(g3);

		affpg.parcoursLargeur(g1);
		affDao.ajouter(affpg);

		assertEquals(affDao.find(affpg.getIdentification()), affpg);
	}

	@Test
	public void testFindAll() {

		AffichageParGroupeDAO affDao = new AffichageParGroupeDAO();

		GroupePersonnel g1 = new GroupePersonnel();
		GroupePersonnel g2 = new GroupePersonnel();
		GroupePersonnel g3 = new GroupePersonnel();

		Personnel personne1 = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		Personnel personne2 = new PersonnelBuilder("BELHABIB", "Riyad", "Secretaire")
				.Naissance(LocalDate.parse("1992-08-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Personnel", "0650784512"))
				.ajouter_numero(new Telephone("Pro", "0650784513")).build();

		AffichageParGroupe affpg = new AffichageParGroupe();

		g1.ajouter(personne1);
		g2.ajouter(personne2);
		g1.ajouter(g2);
		g2.ajouter(g3);

		affpg.parcoursLargeur(g1);
		affDao.ajouter(affpg);

		AffichageParGroupe affpg2 = new AffichageParGroupe();
		GroupePersonnel g4 = new GroupePersonnel();

		g4.ajouter(g2);
		g4.ajouter(personne1);
		g4.ajouter(personne2);
		affpg2.parcoursLargeur(g4);
		affDao.ajouter(affpg2);

		ArrayList<AffichageParGroupe> list = affDao.findAll();

		ArrayList<AffichageParGroupe> comparaison = new ArrayList<AffichageParGroupe>();
		comparaison.add(affpg);
		comparaison.add(affpg2);

		assertEquals(list, comparaison);
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testSerialiser() {
		AffichageParGroupeDAO affgrp = new AffichageParGroupeDAO();

		GroupePersonnel g1 = new GroupePersonnel();
		GroupePersonnel g2 = new GroupePersonnel();
		GroupePersonnel g3 = new GroupePersonnel();

		Personnel personne1 = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		Personnel personne2 = new PersonnelBuilder("BELHABIB", "Riyad", "Secretaire")
				.Naissance(LocalDate.parse("1992-08-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Personnel", "0650784512"))
				.ajouter_numero(new Telephone("Pro", "0650784513")).build();

		AffichageParGroupe affpg = new AffichageParGroupe();

		g1.ajouter(personne1);
		g2.ajouter(personne2);
		g1.ajouter(g2);
		g2.ajouter(g3);

		affpg.parcoursLargeur(g1);
		affgrp.ajouter(affpg);
		affgrp.serialiser("AffGroupeDao");
		AffichageParGroupeDAO affgrp2 = AffichageParGroupeDAO.deSerialiser("AffGroupeDao");
		File f = new File("AffGroupeDao");
		f.delete();

		assertEquals(affgrp.find(0), affgrp2.find(0));
	}
}
