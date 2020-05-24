package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import Abdelhafid.pglp_5_2.Personnel.GroupePersonnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.PersonnelInterface;




public class GroupeDaoJdbc implements DaoJDBC<GroupePersonnel> {

	@Override
	public GroupePersonnel Create(GroupePersonnel groupe) {
		if (Find(groupe.getId_composite()) == null) {

			try (Connection connect = DriverManager.getConnection(Initializer.url)) {

				PreparedStatement prepare = connect.prepareStatement("INSERT  INTO GROUPE (IdGroupe)" + "VALUES (?)");
				prepare.setInt(1, groupe.getId_composite());
				int result = prepare.executeUpdate();


				assert result == 1;

				

				DaoJDBC<Personnel> persdao= FactoryDaoJdbc.getPersonnelDAO();
				
				Iterator<PersonnelInterface> iterateur = groupe.iterator();
				while (iterateur.hasNext()) {

					PersonnelInterface interf = iterateur.next();
					if (interf instanceof Personnel) {
					Personnel p= persdao.Create((Personnel) interf);
					Association.CreateAssociation(groupe.getId_composite(),p.getId());

					} else {
						GroupePersonnel grp = (GroupePersonnel)interf;
						Create(grp);
						Association.CreateAssociation(groupe.getId_composite(),grp.getId_composite());
					}
					
					
				}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
	
			
		}
		return groupe;
		
	}

	@Override
	public GroupePersonnel Find(int id) {

		GroupePersonnel groupe = null;

		try (Connection connect = DriverManager.getConnection(Initializer.url)) {
			PreparedStatement prepare = connect.prepareStatement("SELECT * FROM GROUPE WHERE IdGroupe = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			if (result.next()) {
				groupe = new GroupePersonnel();
			}
			// retrouver toutes les association du groupe dans la table ASSOCIATION
			ArrayList<Personnel> list_pers = Association.Find_All(id);

			for (Personnel p : list_pers) {
				groupe.ajouter(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return groupe;
	}

	@Override
	public GroupePersonnel Update(GroupePersonnel groupe) {
		GroupePersonnel grp = this.Find(groupe.getId_composite());

		if (grp != null) {

			try (Connection connect = DriverManager.getConnection(Initializer.url)) {

				delete(grp);
				Create(groupe);

				return groupe;
			} catch (SQLException e) {
				e.printStackTrace();
				return grp;
			}
		}

		return groupe;
	}

	@Override
	public void delete(GroupePersonnel groupe) {
		if (Find(groupe.getId_composite()) != null) {
			try (Connection connect = DriverManager.getConnection(Initializer.url)) {

				/*
				 * Supprimer toute les associations du groupe vers les differentes formes daans
				 * la table ASSOCIATION
				 */
				Association.Remove_All_Asso(groupe.getId_composite());

				// supprimer le goupe même dans la table ASSOCIATION
				Association.Remove_Association(groupe.getId_composite());

				PreparedStatement prepare = connect.prepareStatement("DELETE FROM GROUPE WHERE IdGroupe = ?");
				prepare.setInt(1, groupe.getId_composite());
				int rs = prepare.executeUpdate();

				assert rs == 1;

			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
	}
	

}
