package Abdelhafid.pglp_5_2.Personnel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class GroupePersonnel implements PersonnelInterface, Iterable<PersonnelInterface>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325828234782586422L;

	private ArrayList<PersonnelInterface> liste_personnels;

	private final int id_composite;
	private static int Compteur = 1;

	public GroupePersonnel() {
		id_composite = Compteur++;
		liste_personnels = new ArrayList<PersonnelInterface>();
	}

	public void print() {
		System.out.println("Id : " + id_composite);
		for (PersonnelInterface per_inter : liste_personnels) {

			per_inter.print();
		}

	}

	/* ajouter une personne à la liste si elle n'éxiste pas déja */

	public GroupePersonnel ajouter(final PersonnelInterface per_inter) {
		if (!liste_personnels.contains(per_inter)) {
			liste_personnels.add(per_inter);
		}
		return this;
	}

	/* Supprimer une personne si elle éxiste dans la liste */

	public GroupePersonnel supprimer(final PersonnelInterface per_inter) {

		if (liste_personnels.contains(per_inter)) {
			liste_personnels.remove(per_inter);
		}
		return this;
	}

	public int getId_composite() {
		return id_composite;
	}

	public Iterator<PersonnelInterface> iterator() {

		return liste_personnels.iterator();
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

	public static GroupePersonnel deSerialiser(final String path) {
		ObjectInputStream in = null;
		GroupePersonnel grp = null;
		try {

			final FileInputStream fichieri = new FileInputStream(path);
			in = new ObjectInputStream(fichieri);
			grp = (GroupePersonnel) in.readObject();

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
		return grp;
	}

}
