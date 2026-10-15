package entity;

import database.PrenotazioneDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class EntityPrenotazione {
	
	private int codice_prenotazione;
	private String tipologia_prenotazione;
	private String targa_veicoloP;
	private EntityCorsa corsa_associata;
	
	public EntityPrenotazione(String tipologia_prenotazione, String targa_veicoloP, EntityCorsa corsa) throws DAOException, DBConnectionException {
		super();
		this.tipologia_prenotazione = tipologia_prenotazione;
		this.targa_veicoloP = targa_veicoloP;
		this.corsa_associata = corsa;
	}
	
	public EntityPrenotazione(String tipologia_prenotazione, EntityCorsa corsa) throws DAOException, DBConnectionException {
		super();
		this.tipologia_prenotazione = tipologia_prenotazione;
		this.corsa_associata = corsa;
	}
	
	public int getCodiceP() {
		return codice_prenotazione;
	}
	public void setCodiceP(int codice_prenotazione) {
		this.codice_prenotazione = codice_prenotazione;
	}
	public String getTipologiaP() {
		return tipologia_prenotazione;
	}
	public void setTipologiaP(String tipologia_prenotazione) {
		this.tipologia_prenotazione = tipologia_prenotazione;
	}
	public String getTargaP() {
		return targa_veicoloP;
	}
	public void setTargaP(String targa_veicoloP) {
		this.targa_veicoloP = targa_veicoloP;
	}
	public EntityCorsa getCorsa_associata() {
		return corsa_associata;
	}
	public void setCorsa(EntityCorsa corsa_da_associare) {
		this.corsa_associata = corsa_da_associare;
	}
	
	public void savePrenotazione() throws DAOException, DBConnectionException {
		PrenotazioneDAO.createPrenotazione(this);
	}
	
	public int ottieni_codice()throws DAOException, DBConnectionException{
		int codice = PrenotazioneDAO.readPrenotazione(this);
		return codice;
	}
	
}