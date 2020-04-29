package Abdelhafid.pglp_5_2.Personnel;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class TestSerializable {

	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException {

		Personnel personne = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.ajouter_numero(new Telephone("Perso", "0650784512")).ajouter_numero(new Telephone("Pro", "0650784513"))
				.build();

		GroupePersonnel g1 = new GroupePersonnel();
		g1.ajouter(personne);

		AffichageParGroupe affgrp = new AffichageParGroupe();
		affgrp.parcoursLargeur(g1);

		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("./objetser")))) {
			out.writeObject(new PersonnelBuilder("BELHABIB", "Riyad", "directeur")
					.Naissance(LocalDate.parse("1994-03-19", DateTimeFormatter.ISO_DATE))
					.ajouter_numero(new Telephone("Perso", "0658784747"))
					.ajouter_numero(new Telephone("Pro", "0650784513")).build());
			out.writeObject(new PersonnelBuilder("B", "joe", "secretaire")
					.Naissance(LocalDate.parse("1998-03-08", DateTimeFormatter.ISO_DATE))
					.ajouter_numero(new Telephone("Perso", "0658778747"))
					.ajouter_numero(new Telephone("Pro", "0650589689")).build());
			out.writeObject(g1);
			out.writeObject(affgrp);
		}

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./objetser")))) {
			Personnel p = (Personnel) in.readObject();

		}

	}

}
