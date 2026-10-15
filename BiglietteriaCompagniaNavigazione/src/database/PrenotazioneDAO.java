package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityPrenotazione;
import exception.DAOException;
import exception.DBConnectionException;

public class PrenotazioneDAO {
	
	public static void createPrenotazione(EntityPrenotazione eP) throws DAOException, DBConnectionException {
		
		try {
			
			Connection conn = DBManager.getConnection();
			
			String query = null;
			if(eP.getTipologiaP().equals("passeggero")) {
				query = "INSERT INTO PRENOTAZIONI VALUES (null,?,null,?);";
			} else {
				query = "INSERT INTO PRENOTAZIONI VALUES (null, ?, ?, ?);";
			}
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, eP.getTipologiaP());
				
				if(eP.getTipologiaP().equals("passeggero")) {
					stmt.setInt(2, eP.getCorsa_associata().getCodiceC());
				} else {
					stmt.setString(2, eP.getTargaP());
					stmt.setInt(3, eP.getCorsa_associata().getCodiceC());
				}

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura prenotazione");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}		
		
	}
	
	public static int read_posti_occupati(String tipologia, int id_corsa) throws DAOException, DBConnectionException {
		int posti = 0;
		
		try {
			Connection conn = DBManager.getConnection();
			
			String query = "SELECT COUNT(*) FROM PRENOTAZIONI WHERE CORSA=? AND TIPOLOGIA=? ;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setInt(1, id_corsa);
				stmt.setString(2, tipologia );
				
				ResultSet result = stmt.executeQuery();

				if(result.next()) {
					posti = result.getInt(1);
				}
				
			}catch(SQLException e) {
				throw new DAOException("Errore lettura posti occupati");
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

		return posti;
	}
	
	public static int readPrenotazione(EntityPrenotazione eP) throws DAOException, DBConnectionException{
		
		int codice = 0;

		try {
			Connection conn = DBManager.getConnection();
			
			String query = "SHOW TABLE STATUS LIKE 'PRENOTAZIONI';";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);				

				ResultSet result = stmt.executeQuery();
				
				if(result.next()) {
					codice = (int) result.getLong(11);
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura database" + e.getMessage());
			} finally {
				DBManager.closeConnection();
			}

		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

		return codice;
		
	}
}	

	