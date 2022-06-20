package server;

import java.io.Serializable;

public class Reading implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	private int valore;
	
	public Reading(String tipo, int valore) {
		this.setTipo(tipo);
		this.setValore(valore);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

}
