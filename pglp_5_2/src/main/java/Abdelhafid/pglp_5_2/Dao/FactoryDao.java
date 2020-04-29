
package Abdelhafid.pglp_5_2.Dao;
import java.io.IOException;


import Abdelhafid.pglp_5_2.Personnel.AffichageParGroupe;
import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.Telephone;

public class FactoryDao {
	
	public static Dao<Telephone> getNumeroTelephoneDAO()
            throws IOException {
        return new TelephoneDAO();
    }

	public static Dao<Personnel> getPersonnelDAO()
            throws IOException {
        return new PersonnelDAO();
    }
	
	
	public static Dao<GroupePersonnel> getGroupePersonnelDAO()
            throws IOException {
        return new GroupePersonnelDAO();
    }
	
	public static Dao<AffichageParGroupe> getAffichageParGroupeDAO()
            throws IOException {
        return new AffichageParGroupeDAO();
    }
	
	 public static Dao<Personnel> getPersonnelDAO(final String deserialiser) {
	        if (deserialiser == null) {
	            return new PersonnelDAO();
	        } else {
	            return PersonnelDAO.deSerialiser(deserialiser);
	        }
	    }
	 
	 
	 public static Dao<GroupePersonnel>
	 getGroupePersonnelDAO(final String deserialiser) {
	        if (deserialiser== null) {
	            return new GroupePersonnelDAO();
	        } else {
	            return GroupePersonnelDAO.deSerialiser(deserialiser);
	        }
	    }
	   
	 
	    public static Dao<AffichageParGroupe>
	    getAffichageParGroupeDAO(final String deserialiser) {
	        if (deserialiser == null) {
	            return new AffichageParGroupeDAO();
	        } else {
	            return AffichageParGroupeDAO.deSerialiser(deserialiser);
	        }
	    }
	
	
	
	
	
}
