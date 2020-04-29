package Abdelhafid.pglp_5_2.Dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Abdelhafid.pglp_5_2.Personnel.Personnel;

public class PersonnelDAO implements Dao<Abdelhafid.pglp_5_2.Personnel.Personnel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7276606536681806759L;
	private ArrayList<Personnel> liste_personnels;

	public PersonnelDAO() {
		this.liste_personnels = new ArrayList<Personnel>();
	}

	@Override

	public void ajouter(Personnel obj) {

		liste_personnels.add(obj);
	}

	@Override
	public Personnel find(int id) {
		for (Personnel personne : liste_personnels) {
			if (personne.getId() == id) {
				return personne;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Personnel> findAll() {

		return (ArrayList<Personnel>) liste_personnels.clone();

	}

	@Override
	public Personnel update(Personnel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Personnel obj) {
		// TODO Auto-generated method stub
		liste_personnels.remove(obj);
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

	public static PersonnelDAO deSerialiser(final String path) {
		ObjectInputStream in = null;
		PersonnelDAO personne = null;
		try {
			final FileInputStream fichierS = new FileInputStream(path);
			in = new ObjectInputStream(fichierS);
			personne = (PersonnelDAO) in.readObject();
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
