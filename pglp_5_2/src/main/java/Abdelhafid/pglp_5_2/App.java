package Abdelhafid.pglp_5_2;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Abdelhafid.pglp_5_2.Jdbc.PersonnelDaoJdbc;
import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.Telephone;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class App 
{


	public static void main( String[] args ) throws SQLException, IOException {
		
		PersonnelDaoJdbc pers= new PersonnelDaoJdbc();
		
		Personnel personne = new PersonnelBuilder("BELHABIB", "Soufiane", "Informaticien")
				.Naissance(LocalDate.parse("1992-06-16", DateTimeFormatter.ISO_DATE))
				.build();
		personne.print();
		
		
		
		 Personnel p= pers.Create(personne);
		 p.print();
       
    }
}
