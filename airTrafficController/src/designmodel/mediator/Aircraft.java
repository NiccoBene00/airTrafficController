package designmodel.mediator;

import java.util.Objects;

public class Aircraft extends ControlTower{

	private AirTrafficControllable controlTowerLanai;
	private String flighID;
	private String aircraftName;
	private int passengersCapacity;
	
	public Aircraft(AirTrafficControllable controlTower, String flightID, String aircraftName, int passengerCapacity) {
		this.controlTowerLanai = controlTower;
		this.flighID = flightID;
		this.aircraftName = aircraftName;
		this.passengersCapacity = passengerCapacity;
	}
	
	public String getFlighID() {
		return flighID;
	}
	
	public void setFlighID(String flighID) {
		this.flighID = flighID;
	}

	public String getAircraftName() {
		return aircraftName;
	}
	
	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}
	
	public int getPassengersCapacity() {
		return passengersCapacity;
	}
	
	public void setPassengersCapacity(int passengersCapacity) {
		this.passengersCapacity = passengersCapacity;
	}
	
	public void taxxing() {
		
		System.out.println("************************************************************");
		
		System.out.println("Available runways: " + this.controlTowerLanai.
				showAvailableRunway());
		
		String allotAirstrip = this.controlTowerLanai.allotRunwayTo(this);
		
		if(!Objects.isNull(allotAirstrip)) {
			
			
			System.out.println(this.aircraftName + "'s [" + this.flighID + "]" + 
					" taxxing on the " + allotAirstrip);
			
			System.out.println("Available runways: " + this.controlTowerLanai.
					showAvailableRunway());
			
			System.out.println("************************************************************");
			
		}else {
			
			System.out.println(this.aircraftName + " [" + this.flighID + "] can't taxi" );
			
			System.out.println(this.aircraftName + " [" + this.flighID + "] requests "
					+ "the control tower for a runway change" );
			
			String newRunway = this.controlTowerLanai.changeRunway(this);
			
			if(!Objects.isNull(newRunway)) {
				
				System.out.println("Control tower confirms the request and it allot " 
				+ newRunway + " to [" + this.flighID + "]");
				
				System.out.println("Available runways: " + 
						this.controlTowerLanai.showAvailableRunway());
				
			}else {
				
				System.out.println("Control tower denys the requests because all the "
						+ "runways are not available");
				
			}
			
			System.out.println("************************************************************");
		}
		
	}
	
	public void takeoff() {
		
		System.out.println("************************************************************");
		
		String releasedAirstrip = this.controlTowerLanai.releaseRunwayOccupiedBy(this);
		
		if(!Objects.isNull(releasedAirstrip)) {
			
			System.out.println(this.aircraftName + " [" + this.flighID + "] take off "
					+ "correctly from " + releasedAirstrip);
			
			System.out.println("Availble runways: " + this.controlTowerLanai.
					showAvailableRunway());
			
			System.out.println("************************************************************");
			
		}
		
	}
	
}
