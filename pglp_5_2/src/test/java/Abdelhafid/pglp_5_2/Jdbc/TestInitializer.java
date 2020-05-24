package Abdelhafid.pglp_5_2.Jdbc;

import org.junit.Test;

public class TestInitializer {

	
	
	@Test
	public void CreateTables() throws TableExist {
	Initializer base_donnes = new Initializer();
	 base_donnes.CreateAllTables();
	 
	}
	
	
	/*
	@Test
	public void dropCreateTables () throws TableExist{
		Initializer base_donnes= new Initializer();
		 base_donnes.supprimerAllTables();
	}
	 */
}
