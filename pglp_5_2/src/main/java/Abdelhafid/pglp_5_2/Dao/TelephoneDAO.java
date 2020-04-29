package Abdelhafid.pglp_5_2.Dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Abdelhafid.pglp_5_2.Personnel.Telephone;

public class TelephoneDAO implements Dao<Abdelhafid.pglp_5_2.Personnel.Telephone>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9202252269391675617L;
	private ArrayList<Telephone> num_telephone;

	public TelephoneDAO() {

		this.num_telephone = new ArrayList<Telephone>();
	}

	@Override
	public void ajouter(Telephone object) {
		num_telephone.add(object);

	}

	@Override

	public Telephone find(int id) {
		for (Telephone tel : num_telephone) {
			if (Integer.parseInt(tel.getNumero()) == id) {

				return tel;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Telephone> findAll() {
		return (ArrayList<Telephone>) num_telephone.clone();

	}

	@Override
	public Telephone update(Telephone obj) {

		return null;
	}

	@Override
	public void delete(Telephone obj) {
		num_telephone.remove(obj);

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

	public static TelephoneDAO deSerialiser(final String path) {
		ObjectInputStream in = null;
		TelephoneDAO telephone = null;
		try {
			final FileInputStream fichierS = new FileInputStream(path);
			in = new ObjectInputStream(fichierS);
			telephone = (TelephoneDAO) in.readObject();
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
		return telephone;
	}

}
