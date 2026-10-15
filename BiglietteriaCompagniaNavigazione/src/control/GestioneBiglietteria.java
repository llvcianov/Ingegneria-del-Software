package control;


import java.sql.Date;
import java.sql.Time;

import database.CorsaDAO;
//import database.ImpiegatoDAO;
//import database.NaveDAO;
import database.PrenotazioneDAO;
//import entity.EntityBiglietto;
import entity.EntityCorsa;
//import entity.EntityImpiegato;
import entity.EntityPrenotazione;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneBiglietteria {
	
	public int posti_disponibili;
	public float prezzo;
	
	private static GestioneBiglietteria gB = null;
	
	private  EntityCorsa eC = null;
	private  EntityCorsa eC1 = null;
	private  EntityPrenotazione eP = null;
	
	
	public static GestioneBiglietteria getInstance() 
	{ 
		if (gB == null) 
			gB = new GestioneBiglietteria(); 

		return gB; 
	}
	
	public void prenotaBiglietto(String tipologia, Date data, Time ora, String porto_partenza, String porto_arrivo) throws OperationException{
		
		int capienza = 0;
		int posti_occupati = 0;

		try {
			
			eC = CorsaDAO.readCorsa(ora, data, porto_partenza, porto_arrivo);
			if(eC == null) {
				throw new OperationException("Corsa non trovata");
			} else {
				eC1 = new EntityCorsa(eC.getCodiceC());
				if (eC1.getNave_associata().getTipo().equals("ALISCAFO") && tipologia.equals("autoveicolo")) {
					throw new OperationException("Corsa non trovata");
				} else {
					System.out.println("Corsa trovata!");
					System.out.println("codice: " + eC1.getCodiceC());				
					System.out.println("nome della nave: " + eC1.getNave_associata().getNome());
				
					if(tipologia.equals("passeggero")) {
						capienza = eC1.getNave_associata().getCapienzaP();
					} else {
						capienza = eC1.getNave_associata().getCapienzaA();
					}
				
					posti_occupati = PrenotazioneDAO.read_posti_occupati(tipologia, eC1.getCodiceC());
				
					if(capienza - posti_occupati <= 0) {
						throw new OperationException("Posti esauriti");
					}
					prezzo = eC1.getPrezzo();
				}
			}					
		} catch (DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		} catch (DAOException ex) {
			throw new OperationException("Ops, qualcosa è andato storto..");
		}
		
	}
		
	public void generaprenotazione(String targa, String tipologia) throws OperationException {
		
		try {
			if(tipologia.equals("autoveicolo")) {
				eP = new EntityPrenotazione(tipologia, targa, eC1);
			} else {
				eP = new EntityPrenotazione(tipologia, eC1);
			}
			eP.savePrenotazione();
	
		}catch(DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		}catch(DAOException e) {
			throw new OperationException("Errore generazione prenotazione");
		}
		
	}
	
	public void generaStampa(String tipologia) throws OperationException {
		
		try {
			
			System.out.println();
			System.out.println("********** STAMPA-PRENOTAZIONE **********");
			
			System.out.println("Codice prenotazione: " + (eP.ottieni_codice()-1));
			System.out.println("Porto di partenza: " + eP.getCorsa_associata().getPortoP());
			System.out.println("Porto di arrivo: " + eP.getCorsa_associata().getPortoA());
			if(tipologia.equals("autoveicolo")){
				System.out.println("Targa: " + eP.getTargaP());
			}
			System.out.println();
			System.out.println("Ricorda di passare in biglietteria con la stampa della prenotazione per l'emissione del tuo biglietto!");
			System.out.println();
			System.out.println("*****************************************"); 
		
		} catch (DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		} catch (DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto.." + ex.getLocalizedMessage());
		}		
	}
}