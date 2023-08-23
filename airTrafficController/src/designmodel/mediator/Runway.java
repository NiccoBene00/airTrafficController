package designmodel.mediator;

public class Runway extends ControlTower {

	private String runwayName;
	private int length;

	public Runway(String runwayName, int length) {

		this.runwayName = runwayName;
		this.length = length;

	}

	public String getRunwayName() {
		return runwayName;
	}

	public void setAirstripName(String runwayName) {
		this.runwayName = runwayName;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
