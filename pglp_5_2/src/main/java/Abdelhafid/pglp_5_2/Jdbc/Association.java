package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Abdelhafid.pglp_5_2.Personnel.Personnel;

public class Association {

	public static void CreateAssociation(final int groupe, final int idpers) {

		try (Connection connect = DriverManager.getConnection(Initializer.url)) {

			PreparedStatement prepare = connect
					.prepareStatement("INSERT INTO ASSOCIATION (Groupe,IdPersonne)" + "VALUES (?,?)");
			prepare.setInt(1, groupe);
			prepare.setInt(2, idpers);
			int result = prepare.executeUpdate();
			assert result == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void Remove_Association(final int idper) {
		try (Connection connect = DriverManager.getConnection(Initializer.url)) {

			PreparedStatement prepare = connect.prepareStatement("DELETE FROM ASSOCIATION WHERE IdPersonne = ?");

			prepare.setInt(1, idper);
			int result = prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Fonction qui supprime dans la table ASSOCIATION les associations vers le
	 * groupe.
	 * 
	 * @param nom_Groupe
	 *            Le nom du groupe a retirer.
	 */
	public static void Remove_All_Asso(final int Groupe) {
		try (Connection connect = DriverManager.getConnection(Initializer.url)) {

			PreparedStatement prepare = connect.prepareStatement("DELETE FROM ASSOCIATION WHERE Groupe = ?");

			prepare.setInt(1, Groupe);
			int result = prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean appartient(final int idpers) {
		try (Connection connect = DriverManager.getConnection(Initializer.url)) {
			PreparedStatement prepare = connect.prepareStatement("SELECT * FROM ASSOCIATION WHERE IdPersonne = ?");
			prepare.setInt(1, idpers);
			ResultSet result = prepare.executeQuery();
			return result.next();
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Fonction qui cherche dans la table ASSOCIATION les forme contenues dans un
	 * groupe .
	 * 
	 * @param nom_Groupe
	 *            Le nom du groupe.
	 * @return liste ds formes.
	 */
	public static ArrayList<Personnel> Find_All(final int Groupe) {
		ArrayList<Personnel> list = new ArrayList<Personnel>();
		try (Connection connect = DriverManager.getConnection(Initializer.url)) {

			PreparedStatement prepare = connect
					.prepareStatement("SELECT IdPersonne FROM ASSOCIATION WHERE Groupe = ? ");

			prepare.setInt(1, Groupe);

			ResultSet result = prepare.executeQuery();

			while (result.next()) {

				PersonnelDaoJdbc persd = (PersonnelDaoJdbc) FactoryDaoJdbc.getPersonnelDAO();

				Personnel find = persd.Find(result.getInt("IdPersonne"));

				if (find != null) {

					list.add(find);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			new ArrayList<Personnel>();
		}

		return list;

	}

}
