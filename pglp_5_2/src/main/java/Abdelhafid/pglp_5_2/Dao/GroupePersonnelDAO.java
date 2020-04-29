package Abdelhafid.pglp_5_2.Dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;

public class GroupePersonnelDAO implements Dao<Abdelhafid.pglp_5_2.Personnel.GroupePersonnel>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5006222576934875703L;
	private ArrayList<GroupePersonnel> liste_personnels;

	public GroupePersonnelDAO() {

		this.liste_personnels = new ArrayList<GroupePersonnel>();
	}

	@Override
	public void ajouter(GroupePersonnel object) {
		liste_personnels.add(object);

	}

	@Override
	public GroupePersonnel find(int id) {
		for (GroupePersonnel gp : liste_personnels) {
			if (gp.getId_composite() == id) {
				return gp;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<GroupePersonnel> findAll() {

		return (ArrayList<GroupePersonnel>) liste_personnels.clone();
	}

	@Override
	public GroupePersonnel update(GroupePersonnel obj) {

		return null;
	}

	@Override
	public void delete(GroupePersonnel obj) {

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

	public static GroupePersonnelDAO deSerialiser(final String path) {
		ObjectInputStream in = null;
		GroupePersonnelDAO grp = null;
		try {
			final FileInputStream fichierS = new FileInputStream(path);
			in = new ObjectInputStream(fichierS);
			grp = (GroupePersonnelDAO) in.readObject();
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