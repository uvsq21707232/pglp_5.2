package Abdelhafid.pglp_5_2.Jdbc;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
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

			/*
			 * ResultSet result2 = databaseMetadata.getTables(null, null, "GROUPE", null);
			 * if (result.next()) { throw new TableExist("TABLE ALREADY EXISTS"); } else {
			 * state.addBatch("CREATE TABLE groupe (" + "id VARCHAR(100) PRIMARY KEY" +
			 * ")");}
			 * 
			 */

			state.executeBatch();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/*
	public void dropCreateTables() throws SQLException {
		 Statement sTMT = null;
		try (Connection connect = DriverManager.getConnection(url)) {
			
			sTMT = connect.createStatement();
			
			try {
				sTMT.execute("DROP TABLE PERSONNE");
	        } catch (SQLException e) {
	        }

			try {
				sTMT.execute("DROP TABLE TELEPHONE");
	        } catch (SQLException e) {
	        }
			
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	*/	
	
}
