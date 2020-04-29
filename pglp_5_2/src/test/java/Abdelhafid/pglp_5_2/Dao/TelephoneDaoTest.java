package Abdelhafid.pglp_5_2.Dao;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import Abdelhafid.pglp_5_2.Personnel.Telephone;

public class TelephoneDaoTest {

	@Test
	public void testAjout() {
		TelephoneDAO telephones = new TelephoneDAO();

		Telephone tel = new Telephone("perso", "5858");

		telephones.ajouter(tel);

		assertEquals(telephones.find(5858), tel);

	}

	@Test

	public void testDelete() {
		TelephoneDAO telephones = new TelephoneDAO();

		Telephone tel = new Telephone("perso", " 5858");
		telephones.ajouter(tel);
		telephones.delete(tel);

		assertEquals(telephones.find(5858), null);

	}

	@Test
	public void testFindAll() {
		TelephoneDAO telephones = new TelephoneDAO();
		Telephone tel = new Telephone("perso", " 5858");
		Telephone tel1 = new Telephone("Pro", " 3838 ");
		Telephone tel2 = new Telephone("personnel", " 3123");
		telephones.ajouter(tel);
		telephones.ajouter(tel1);
		telephones.ajouter(tel2);

		ArrayList<Telephone> num_phone = telephones.findAll();

		ArrayList<Telephone> num_phones = new ArrayList<Telephone>();
		num_phones.add(tel);
		num_phones.add(tel1);
		num_phones.add(tel2);

		assertEquals(num_phone, num_phones);

	}

	@Test
	public void testUpdate() {

	}

}
