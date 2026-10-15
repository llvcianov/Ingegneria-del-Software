package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import entity.EntityCorsa;
import entity.EntityNave;
import exception.DAOException;
import exception.DBConnectionException;

public class CorsaDAO {
	
	public static EntityCorsa readCorsa(Time ora, Date data, String porto_partenza, String porto_arrivo) throws DAOException, DBConnectionException {
		
		EntityCorsa eC = null;
		try {

			Connection conn = DBManager.getConnection();

			try {
				
				String query = "SELECT * FROM `corse` WHERE `ora` = ? AND `data` = ? AND `porto_partenza` LIKE ? AND `porto_arrivo` LIKE ?;";
				
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setTime(1, ora);
				stmt.setDate(2, data);
				stmt.setString(3, porto_partenza);
				stmt.setString(4, porto_arrivo);
				
				ResultSet result = stmt.executeQuery();
								
				if(result.next()) {
	                eC = new EntityCorsa(result.getInt(1), result.getTime(2), result.getDate(3), result.getFloat(4), result.getString(5), result.getString(6), result.getString(7));	                
	            }

			}catch(SQLException e) {
				throw new DAOException("Errore lettura corsa");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}

		return eC;
	}
	
	public static EntityCorsa initCorsa(int codice_corsa) throws DAOException, DBConnectionException
	{
		EntityCorsa eC = null;
		try {

			Connection conn = DBManager.getConnection();

			try {
				
				String query = "SELECT * FROM `corse` c JOIN `navi` n ON (c.`nave`=n.`nome`) WHERE c.`codice`=?;";

				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, codice_corsa);
				
				ResultSet result = stmt.executeQuery();
				
				if(result.next()) {
					
					EntityNave eN = new EntityNave(result.getString(8),result.getInt(9),result.getInt(10), result.getString(11));
					eC= new EntityCorsa(result.getInt(1),result.getDate(2),result.getDate(3),result.getFloat(4),result.getString(5),result.getString(6),eN);
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura database");
			}finally {
				DBManager.closeConnection();
			}
			
		}catch(SQLException e) {
			throw new DBConnectionException("Errore di connessione DB");
		}
		return eC;
	}

}