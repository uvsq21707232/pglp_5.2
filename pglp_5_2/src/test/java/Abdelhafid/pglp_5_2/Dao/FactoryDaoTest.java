package Abdelhafid.pglp_5_2.Dao;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.AffichageParGroupe;
import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel;

public class FactoryDaoTest {

	@Test
	public void testPeRSDAO() throws IOException {
		Dao<Personnel> dao = FactoryDao.getPersonnelDAO();
		assertTrue(dao.findAll().isEmpty());
	}

	@Test
	public void testGroupeDAO() throws IOException {
		Dao<GroupePersonnel> dao = FactoryDao.getGroupePersonnelDAO();
		assertTrue(dao.findAll().isEmpty());
	}

	@Test
	public void testAffGroupeDAO() throws IOException {
		Dao<AffichageParGroupe> dao = FactoryDao.getAffichageParGroupeDAO();
		assertTrue(dao.findAll().isEmpty());
	}

}
