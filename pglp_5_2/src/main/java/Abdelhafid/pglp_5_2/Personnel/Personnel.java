package Abdelhafid.pglp_5_2.Personnel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public final class Personnel implements PersonnelInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1350092881346723535L;

	private final int id;
	/**
	 * Identifiant du composite suivant.
	 */
	private static int idCom = 1;

	private final String Nom;
	private final String Prenom;
	private ArrayList<Telephone> num_telephones;

	private final String fonction;
	private final LocalDate date_naissance;

	private Personnel(PersonnelBuilder builder) {
		id = idCom++;
		Nom = builder.Nom;
		Prenom = builder.Prenom;
		fonction = builder.fonction;
		date_naissance = builder.date_naissance;
		num_telephones = builder.num_telephones;

	}

	/* Builder */

	public static class PersonnelBuilder {

		private final String Nom;
		private final String Prenom;
		private final String fonction;
		private ArrayList<Telephone> num_telephones = new ArrayList<Telephone>();
		private LocalDate date_naissance;

		public PersonnelBuilder(String nom, String prenom, String fonction) {

			this.Nom = nom;
			this.Prenom = prenom;
			this.fonction = fonction;
		}

		public PersonnelBuilder Naissance(LocalDate naissance) {

			this.date_naissance = naissance;
			return this;

		}

		public PersonnelBuilder ajouter_numero(Telephone Tel_numero) {
			this.num_telephones.add(Tel_numero);
			return this;
		}

		public Personnel build() {
			return new Personnel(this);
		}

	}

	public ArrayList<Telephone> getNum_telephones() {

		return (ArrayList<Telephone>) this.num_telephones.clone();
	}

	public LocalDate getDate_naissance() {
		return date_naissance;
	}

	public String getNom() {
		return Nom;
	}

	public String getPrenom() {
		return Prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public int getId() {
		return this.id;
	}

	public void print() {
		System.out.println(Nom + " " + Prenom + " \nFonction: " + fonction + "\ndate de naissance: " + date_naissance);
		for (Telephone phone : num_telephones) {
			System.out.println("typeTéléphone : " + phone.getTel_type() + " **" + "  numero:" + phone.getNumero());
		}
		System.out.println("\n");

	}

	public void serialiser(final String path) {
		ObjectOutputStream out = null;
		try {
			final FileOutputStream fichierO = new FileOutputStream(path);
			out = new ObjectOutputStream(fichierO);
			out.writeObject(this);
			out.flush();
			out.close();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (final java.io.IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Personnel deSerialiser(final String path) {
		ObjectInputStream in = null;
		Personnel personne = null;
		try {
			final FileInputStream fichierS = new FileInputStream(path);
			in = new ObjectInputStream(fichierS);
			personne = (Personnel) in.readObject();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (final java.io.IOException ex) {
				ex.printStackTrace();
			}
		}
		return personne;
	}
}
