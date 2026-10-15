package entity;

public class EntityNave {
	
	private String nome;
	private int capienza_passeggeri;
	private int capienza_veicoli;
	private String tipo_nave;
	
	public EntityNave(String nome) {
		super();
		this.nome = nome;
		//
	}
	
	public EntityNave(String nome, int capienza_passeggeri, int capienza_veicoli, String tipo_nave) {
		super();
		this.nome = nome;
		this.capienza_passeggeri = capienza_passeggeri;
		this.capienza_veicoli = capienza_veicoli;
		this.tipo_nave = tipo_nave;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCapienzaP() {
		return capienza_passeggeri;
	}
	public void setCapienzaP(int capienza_passeggeri) {
		this.capienza_passeggeri = capienza_passeggeri;
	}
	public int getCapienzaA() {
		return capienza_veicoli;
	}
	public void setCapienzaA(int capienza_veicoli) {
		this.capienza_veicoli = capienza_veicoli;
	}
	public String getTipo() {
		return tipo_nave;
	}
	public void setTipo(String tipo_nave) {
		this.tipo_nave = tipo_nave;
	}
	
}