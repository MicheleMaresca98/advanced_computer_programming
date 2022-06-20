package alertnotification;

import java.io.Serializable;

public class AlertNotification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3423722147905891688L;
	private int componentID;
	private int criticality;
	
	public AlertNotification() {
		this.setComponentID(0);
		this.setCriticality(0);
	}
	
	public AlertNotification(int componentID, int criticality) {
		this.setComponentID(componentID);
		this.setCriticality(criticality);
	}

	public int getComponentID() {
		return componentID;
	}

	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}

	public int getCriticality() {
		return criticality;
	}

	public void setCriticality(int criticality) {
		this.criticality = criticality;
	}

}
