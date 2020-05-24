package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.SQLException;

public interface DaoJDBC<T> {
	
	 T Create(T object) ;

	 T Find(int id);
	 
	 T Update(T object) ;
	 
	 void delete(T obj);

	
	

}
