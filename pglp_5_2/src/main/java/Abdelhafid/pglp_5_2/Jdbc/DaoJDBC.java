package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.SQLException;

public interface DaoJDBC<T> {
	
	 T Create(T object) ;

	 T Find(int id);

	 void delete(T obj);

	
	

}
