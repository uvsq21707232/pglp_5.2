package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.*;
import java.util.Properties;

public class Initializer {

	static final String userName = "";
	private static final String password = "";

	public static String url = "jdbc:derby:DB;create=true";

	public Initializer() {

		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("user", password);
	}

	public void CreateAllTables() throws TableExist {

		try (Connection connect = DriverManager.getConnection(url)) {
			Statement state = connect.createStatement();
			DatabaseMetaData databaseMetadata = connect.getMetaData();
			ResultSet result = databaseMetadata.getTables(null, null, "PERSONNE", null);

			if (result.next()) {
				throw new TableExist("TABLE ALREADY EXISTS");
			} else {

				state.addBatch(

						"CREATE TABLE PERSONNE(" + "Id int," + "Nom VARCHAR(20) NOT NULL,"
								+ "Prenom VARCHAR(20) NOT NULL," + "Fonction VARCHAR(30) NOT NULL,"
								+ "DateNaisssance Date NOT NULL," + "PRIMARY KEY(ID)" + ")");
			}

			ResultSet result1 = databaseMetadata.getTables(null, null, "TELEPHONE", null);
			if (result1.next()) {
				throw new TableExist("TABLE ALREADY EXISTS");
			} else {
				state.addBatch("CREATE TABLE TELEPHONE (" + "numero VARCHAR(20)," + "type VARCHAR(20) NOT NULL,"
						+ "IdPers int NOT NULL," + "PRIMARY KEY(numero),"
						+ "FOREIGN KEY (IdPers) REFERENCES PERSONNE (Id)" + ")");
			}

			ResultSet result_grp = databaseMetadata.getTables(null, null, "GROUPE", null);
			if (result_grp.next()) {
				throw new TableExist("TABLE ALREADY EXISTS");

			} else {

				state.addBatch("CREATE TABLE GROUPE(" + "IdGroupe int ," + "PRIMARY KEY(IdGroupe ),"
						+ "FOREIGN KEY(IdGroupe) REFERENCES GROUPE(IdGroupe )" + ")");
			}

			ResultSet result_Ass = databaseMetadata.getTables(null, null, "ASSOCIATION", null);
			if (result_Ass.next()) {
				throw new TableExist("TABLE ALREADY EXISTS");

			} else {
				

				state.addBatch("CREATE TABLE ASSOCIATION(" + "Groupe int ," + "IdPersonne int, "
						+ "PRIMARY KEY(Groupe,IdPersonne)," + " FOREIGN KEY(Groupe) REFERENCES GROUPE(IdGroupe),"
						+ "FOREIGN KEY(IdPersonne) REFERENCES PERSONNE(Id)" + ")");
			}
			
			

			state.executeBatch();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	}

	/**
	 * Fonction de supression des tables.
	 */
	public void supprimerAllTables() {

		try (Connection connect = DriverManager.getConnection(url)) {
			Statement state = connect.createStatement();

			state.execute("DROP TABLE ASSOCIATION");
			state.execute("DROP TABLE TELEPHONE");
			state.execute("DROP TABLE PERSONNE");
			state.execute("DROP TABLE GROUPE");
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
}
