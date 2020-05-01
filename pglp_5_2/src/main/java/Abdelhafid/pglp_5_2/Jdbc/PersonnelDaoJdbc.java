package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.DriverManager;
import Abdelhafid.pglp_5_2.Personnel.Personnel;
import Abdelhafid.pglp_5_2.Personnel.Personnel.PersonnelBuilder;

public class PersonnelDaoJdbc implements DaoJDBC<Personnel> {

	@Override
	public Personnel Create(Personnel obj) {

		if (Find(obj.getId()) == null) {
			try (Connection connect = DriverManager.getConnection(Initializer.url)) {

				PreparedStatement prepare = connect
						.prepareStatement("INSERT  INTO PERSONNE ( Id,Nom, Prenom, Fonction, DateNaisssance )"
								+ "VALUES (?, ?, ?, ?, ?)");

				prepare.setInt(1, obj.getId());
				prepare.setString(2, obj.getNom());
				prepare.setString(3, obj.getPrenom());
				prepare.setString(4, obj.getFonction());
				prepare.setDate(5, Date.valueOf(obj.getDate_naissance()));
				int result = prepare.executeUpdate();
				assert result == 1;

				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM PERSONNE");

				ResultSet resultat1 = stmt.executeQuery();

				System.out.println("---Table Personne:---\n");
				System.out.println("id\t nom\t prenom\t fonction\t Naissance");

				while (resultat1.next()) {
					System.out.printf("%d\t%s\t%s\t%s\t%s%n", resultat1.getInt("id"), resultat1.getString("nom"),
							resultat1.getString("prenom"), resultat1.getString("fonction"),
							resultat1.getString("DateNaisssance"));
				}
				System.out.println("---------------------" + "---------------\n");

				resultat1.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return obj;
	}

	@Override
	public Personnel Find(int id) {

		Personnel personne = null;
		try (Connection connect = DriverManager.getConnection(Initializer.url)) {
			PreparedStatement prepare = connect.prepareStatement("SELECT * FROM PERSONNE WHERE Id = ?  ");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.next()) {

				personne = new PersonnelBuilder(result.getString("Nom"), result.getString("Prenom"),
						result.getString("Fonction")).Naissance(result.getDate("DateNaisssance").toLocalDate()).build();
				personne.setId(id);

				result.close();

			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return personne;

	}

	@Override
	public void delete(Personnel obj) {
		if (Find(obj.getId()) != null) {

			try (Connection connect = DriverManager.getConnection(Initializer.url)) {

				PreparedStatement prepare = connect.prepareStatement("DELETE FROM PERSONNE " + "WHERE Id = ?");
				prepare.setInt(1, obj.getId());
				int result = prepare.executeUpdate();
				assert result == 1;

				PreparedStatement stmt = connect.prepareStatement("SELECT * FROM PERSONNE");

				ResultSet resultat1 = stmt.executeQuery();

				System.out.println("---Table Personne:---\n");
				System.out.println("id\t nom\t prenom\t fonction\t Naissance");

				while (resultat1.next()) {
					System.out.printf("%d\t%s\t%s\t%s\t%s%n", resultat1.getInt("id"), resultat1.getString("nom"),
							resultat1.getString("prenom"), resultat1.getString("fonction"),
							resultat1.getString("DateNaisssance"));
				}
				System.out.println("---------------------" + "---------------\n");

				resultat1.close();

			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
	}
}
