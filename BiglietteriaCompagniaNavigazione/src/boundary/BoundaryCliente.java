package boundary;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import control.GestioneBiglietteria;
import exception.OperationException;

public class BoundaryCliente {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {		
		boolean exit = false;
		
		while(!exit) {
			System.out.println("1. PrenotaBiglietto");
			System.out.println("2. Visualizza corse giornaliere");
			System.out.println("3. Esci");
			String op = scan.nextLine();

			if(op.equals("1")) {
				PrenotaBiglietto();
			} else if(op.equals("3")){
				exit = true;
			}else{
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}	
		
		System.out.println("Fine!");
		
	}
	
	private static void PrenotaBiglietto() {
		
		boolean inputValido = false;
		GestioneBiglietteria gestioneBiglietteria = GestioneBiglietteria.getInstance();
		String tipologia = null;
		String porto_partenza = null;
		String porto_arrivo = null;
		Date data = null;
		Time ora = null;
		String targa = null;
		String alfanumerico = "^[a-zA-Z0-9]+$";
		String lettera = "^[a-zA-Z ]+$";
		
		try {
			
			while (!inputValido) {
				System.out.println("Inserisci la tipologia della prenotazione (autoveicolo/passeggero):");
				tipologia = scan.nextLine();
				if (tipologia.equals("autoveicolo") || tipologia.equals("passeggero")) {
					inputValido = true;
				} else {
					System.out.println("Tipologia non valida, riprovare...");
				}
			}
			
			inputValido = false;
			while (!inputValido) {
				try {
					
					System.out.println("Inserisci la data (aaaa-MM-gg):");
					String dataTemp = scan.nextLine();
					data = Date.valueOf(dataTemp);

					inputValido = true;
				} catch (IllegalArgumentException iE) {
					System.out.println("Data non valida, riprovare..");
					System.out.println();
				}
			}
			
			inputValido = false;
			while (!inputValido) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				sdf.setLenient(false);
				try {

					System.out.println("Inserisci l'orario (HH:mm):");
					String orarioTemp = scan.nextLine();
					ora = new Time(sdf.parse(orarioTemp).getTime());
					
					sdf.setLenient(true);
					inputValido = true;
				} catch (IllegalArgumentException | ParseException iE) {
					System.out.println("Orario non valido, riprovare...");
					System.out.println();
				}
			}
			
			
			
			inputValido = false;
			while (!inputValido) {
				System.out.println("Inserisci il porto di partenza:");
				porto_partenza = scan.nextLine();
				if (porto_partenza.length() < 100 &&  porto_partenza.matches(lettera)) {
					inputValido = true;
				} else {
					System.out.println("Porto di partenza non valido, riprovare...");
				}	
			}
			
			inputValido = false;
			while (!inputValido) {
				System.out.println("Inserisci il porto di arrivo:");
				porto_arrivo = scan.nextLine();
				if (porto_arrivo.length() < 100 &&  porto_partenza.matches(lettera)) {
					inputValido = true;
				} else {
					System.out.println("Porto di arrivo non valido, riprovare...");
				}	
			}
			
			gestioneBiglietteria.prenotaBiglietto(tipologia, data, ora, porto_partenza, porto_arrivo);
			
			if(tipologia.equals("autoveicolo")) {
					inputValido = false;
					while(!inputValido) {
						System.out.println("Inserisci la targa dell'autoveicolo (7 caratteri alfanumerici):");
						targa = scan.nextLine();
						if(targa.length() == 7 &&  targa.matches(alfanumerico)) {
							inputValido = true;
						} else {
							System.out.println("Targa non valida, riprovare...");
						}
					}
				}
			
			System.out.println("Prezzo: " + gestioneBiglietteria.prezzo + " euro");
			System.out.println("Digita 'S' per confermare o qualunque altro carattere per annullare..");
			String conferma = scan.nextLine();

			if (!conferma.equals("S") && !conferma.equals("s")) {
				System.out.println("Operazione annullata..");
				System.out.println();
				return;
			}
			
			System.out.println();
			System.out.println("Pagamento in corso..");
			TimeUnit.SECONDS.sleep(3);
			System.out.println("Pagamento effettuato!");
			
			gestioneBiglietteria.generaprenotazione(targa, tipologia);
			
			System.out.println("Biglietto prenotato!");
			System.out.println("Rimani in attesa per ricevere la stampa della tua prenotazione...");
			TimeUnit.SECONDS.sleep(3);
			gestioneBiglietteria.generaStampa(tipologia);
			
			System.out.println();
			
			
		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare..\n");
		} catch (Exception e) {
			System.out.println("Unexpected exception, riprovare.." + e.getMessage());
			System.out.println();
		}
		
	}
		
}