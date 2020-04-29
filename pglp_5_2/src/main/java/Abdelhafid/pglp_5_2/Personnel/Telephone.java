package Abdelhafid.pglp_5_2.Personnel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Telephone implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -42640595796165094L;
	private String tel_type;
	private String numero;
	
	public Telephone(String tel_type, String numero) {
	
		this.tel_type = tel_type;
		this.numero = numero;
	}


	public String afficher() {
		
	return  tel_type + " -- " + numero;
	
	}

	public String getTel_type() {
		return tel_type;
	}

	

	public String getNumero() {
		return numero;
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
	
	
	public static Telephone deSerialiser(final String path) {
        ObjectInputStream in = null;
       Telephone telephone = null;
        try {
            final FileInputStream fichierS = new FileInputStream(path);
            in = new ObjectInputStream(fichierS);
            telephone = (Telephone) in.readObject();
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
