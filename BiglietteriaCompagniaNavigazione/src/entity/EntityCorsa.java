package entity;

import java.util.Date;

import database.CorsaDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class EntityCorsa {
	
	private int codice_corsa;
	private Date ora_corsa;
	private Date data_corsa;
	private float prezzo;
	private String porto_partenza;
	private String porto_arrivo;
	private EntityNave nave_associata;
	
	public EntityCorsa(){
		super();
		codice_corsa=0;
		ora_corsa=new Date();
		data_corsa=new Date();
		prezzo=0;
		porto_partenza = new String();
		porto_arrivo = new String();
		nave_associata = null;
	}

	public EntityCorsa(int codice_corsa) throws DAOException, DBConnectionException {
		super();
		EntityCorsa eC = CorsaDAO.initCorsa(codice_corsa);
		this.codice_corsa = codice_corsa;
		this.ora_corsa=eC.getOraC();
		this.data_corsa=eC.getDataC();
		this.prezzo=eC.getPrezzo();
		this.porto_partenza=eC.getPortoP();
		this.porto_arrivo=eC.getPortoA();
		this.nave_associata=eC.getNave_associata();
	}
	
	public EntityCorsa(int codice_corsa, Date ora_corsa, Date data_corsa, float prezzo, String porto_partenza, String porto_arrivo, EntityNave Nave) {
		super();
		this.codice_corsa = codice_corsa;
		this.ora_corsa = ora_corsa;
		this.data_corsa = data_corsa;
		this.prezzo = prezzo;
		this.porto_partenza = porto_partenza;
		this.porto_arrivo = porto_arrivo;
		this.nave_associata = Nave;
	}
	
	public EntityCorsa(int codice_corsa, Date ora_corsa, Date data_corsa, float prezzo, String porto_partenza, String porto_arrivo, String Nave) {
		super();
		this.codice_corsa = codice_corsa;
		this.ora_corsa = ora_corsa;
		this.data_corsa = data_corsa;
		this.prezzo = prezzo;
		this.porto_partenza = porto_partenza;
		this.porto_arrivo = porto_arrivo;
		this.nave_associata = new EntityNave(Nave);
	}
	
	public int getCodiceC() {
		return codice_corsa;
	}
	public void setCodiceC(int codice_corsa) {
		this.codice_corsa = codice_corsa;
	}
	public Date getOraC() {
		return ora_corsa;
	}
	public void setOraC(Date ora_corsa) {
		this.ora_corsa = ora_corsa;
	}
	public Date getDataC() {
		return data_corsa;
	}
	public void setDataC(Date data_corsa) {
		this.data_corsa = data_corsa;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getPortoP() {
		return porto_partenza;
	}
	public void setPortoP(String porto_partenza) {
		this.porto_partenza = porto_partenza;
	}
	public String getPortoA() {
		return porto_arrivo;
	}
	public void setPortoA(String porto_arrivo) {
		this.porto_arrivo = porto_arrivo;
	}
	public EntityNave getNave_associata() {
		return nave_associata;
	}
	public void setNave(EntityNave nave_da_associare) {
		this.nave_associata = nave_da_associare;
	}

}