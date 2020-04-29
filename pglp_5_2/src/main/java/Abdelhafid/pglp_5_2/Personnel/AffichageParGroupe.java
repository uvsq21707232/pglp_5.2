package Abdelhafid.pglp_5_2.Personnel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;

import java.util.Iterator;

public class AffichageParGroupe implements Iterable<PersonnelInterface>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 39135549156587357L;

	private ArrayDeque<PersonnelInterface> file;

	private final int identification;
	private static int Compteur = 1;

	public AffichageParGroupe() {
		identification = Compteur++;

		file = new ArrayDeque<PersonnelInterface>();
	}

	public void parcoursLargeur(final PersonnelInterface p_i) {

		if (p_i.getClass() == GroupePersonnel.class) {

			PersonnelInterface A, B;
			GroupePersonnel grp;

			file = new ArrayDeque<PersonnelInterface>();

			ArrayDeque<PersonnelInterface> temp = new ArrayDeque<PersonnelInterface>();
			temp.add(p_i);

			while (!temp.isEmpty()) {
				A = temp.pollFirst();
				file.add(A);

				if (A.getClass() == GroupePersonnel.class) {

					grp = (GroupePersonnel) A;
					Iterator<PersonnelInterface> ite = grp.iterator();

					while (ite.hasNext()) {
						B = ite.next();
						if (!temp.contains(B) && !file.contains(B)) {
							temp.add(B);

						}

					}

				}
			}

		}
	}

	public int getIdentification() {
		return identification;
	}

	public Iterator<PersonnelInterface> iterator() {

		return file.iterator();
	}

	public void add(final PersonnelInterface ip) {
		file.add(ip);
	}

	public void supp() {
		while (!file.isEmpty()) {
			file.removeFirst();
		}
	}

	public String afficherParcour() {
		String s = "";
		GroupePersonnel grp;
		// affichage du parcours

		for (PersonnelInterface f : file) {
			if (f.getClass() == GroupePersonnel.class) {
				grp = (GroupePersonnel) f;
				s += grp.getId_composite() + "\n";
			} else {
				s += ((Personnel) f).toString();
			}
		}
		return s;

	}

	public String toString() {
		String s = "";
		for (PersonnelInterface ip2 : file) {
			if (ip2.getClass() == GroupePersonnel.class) {
				s += ((GroupePersonnel) ip2).getId_composite() + "\n";
			} else {
				s += ip2.toString();
			}
		}
		return s + "\n";
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

	public static AffichageParGroupe deSerialiser(final String path) {
		ObjectInputStream in = null;
		AffichageParGroupe affgrp = null;
		try {

			final FileInputStream fichieri = new FileInputStream(path);
			in = new ObjectInputStream(fichieri);
			affgrp = (AffichageParGroupe) in.readObject();

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
		return affgrp;
	}

}
