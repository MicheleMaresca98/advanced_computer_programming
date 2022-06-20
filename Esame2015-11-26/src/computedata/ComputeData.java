package computedata;

import java.io.Serializable;

public class ComputeData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1061851149895427688L;
	private String operazione;
	private int operando1;
	private int operando2;
	
	public ComputeData() {
		setOperazione(null);
		setOperando1(0);
		setOperando2(0);
	}
	
	public ComputeData(String operazione, int operando1, int operando2) {
		this.setOperazione(operazione);
		this.setOperando1(operando1);
		this.setOperando2(operando2);
	}

	public String getOperazione() {
		return operazione;
	}

	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}

	public int getOperando1() {
		return operando1;
	}

	public void setOperando1(int operando1) {
		this.operando1 = operando1;
	}

	public int getOperando2() {
		return operando2;
	}

	public void setOperando2(int operando2) {
		this.operando2 = operando2;
	}
	
	public String toString() {
		return operazione + ", " + operando1 + ", " + operando2;
	}
	

}
