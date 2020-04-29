package Abdelhafid.pglp_5_2.Dao;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import Abdelhafid.pglp_5_2.Personnel.AffichageParGroupe;

public class AffichageParGroupeDAO implements Dao<Abdelhafid.pglp_5_2.Personnel.AffichageParGroupe>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866998930386693336L;
	private ArrayList<AffichageParGroupe> affList;

	public AffichageParGroupeDAO() {

		this.affList = new ArrayList<AffichageParGroupe>();

	}

	@Override
	public void ajouter(AffichageParGroupe object) {

		affList.add(object);

	}

	@Override
	public AffichageParGroupe find(int id) {
		for (AffichageParGroupe aff : affList) {
			if (aff.getIdentification() == id) {
				return aff;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AffichageParGroupe> findAll() {

		return (ArrayList<AffichageParGroupe>) affList.clone();
	}

	@Override
	public AffichageParGroupe update(AffichageParGroupe obj) {

		return null;
	}

	@Override
	public void delete(AffichageParGroupe obj) {
		affList.remove(obj);

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

	public static AffichageParGroupeDAO deSerialiser(final String path) {
		ObjectInputStream in = null;
		AffichageParGroupeDAO affgrp = null;
		try {
			final FileInputStream fichierS = new FileInputStream(path);
			in = new ObjectInputStream(fichierS);
			affgrp = (AffichageParGroupeDAO) in.readObject();
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
