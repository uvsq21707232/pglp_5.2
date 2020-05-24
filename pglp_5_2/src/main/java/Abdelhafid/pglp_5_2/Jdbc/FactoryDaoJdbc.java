package Abdelhafid.pglp_5_2.Jdbc;

import java.io.IOException;

import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel;

public class FactoryDaoJdbc {
	
	public static DaoJDBC<Personnel> getPersonnelDAO()
           {
        return new PersonnelDaoJdbc();
    }
	
	public static DaoJDBC<GroupePersonnel> getGroupeDaoJdbc()
       {
        return new GroupeDaoJdbc();
    }
	

}
