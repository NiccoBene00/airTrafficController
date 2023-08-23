package designmodel.mediator;

import java.util.HashMap;
import java.util.LinkedList;

public class LanaiAirPolicie implements AirTrafficControllable {

	private String airportName;
	private LinkedList<Runway> registeredRunways = new LinkedList<>();
	private LinkedList<Runway> availableRunways = new LinkedList<>();
	private HashMap<Aircraft, Runway> mapRunwayAircraft = new HashMap<>();

	public LanaiAirPolicie(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	@Override
	public boolean registerRunway(Runway runway) {

		boolean alreadyRegistered = false;
		boolean registerOutcome = false;

		for (Runway registeredRunway : registeredRunways) {
			if (registeredRunway.equals(runway)) {
				alreadyRegistered = true;
			}
		}

		if (!alreadyRegistered) {
			this.registeredRunways.add(runway);
			this.availableRunways.add(runway);
			registerOutcome = true;
		}

		return registerOutcome;
	}

	@Override
	public boolean unregisterRunway(Runway runway) {

		boolean unregisterOutcome = false;

		for (Runway registeredRunway : this.registeredRunways) {
			if (registeredRunway.equals(runway)) {
				unregisterOutcome = true;
				this.registeredRunways.remove(runway);
				this.availableRunways.remove(runway);
			}
		}

		return unregisterOutcome;
	}

	@Override
	public String showAvailableRunway() {

		String runwayList = "no-one available runway";

		if (!availableRunways.isEmpty()) {

			runwayList = "|";

			for (Runway runway : this.availableRunways) {
				runwayList = runwayList + runway.getRunwayName() + "|";
			}

		}

		return runwayList;
	}

	@Override
	public String allotRunwayTo(Aircraft aircraft) {

		String allotRunway = null;

		if (!this.availableRunways.isEmpty()) {

			for (Runway runway : this.availableRunways) {

				if (runway.getRunwayName().equals("Runway1") && aircraft.getFlighID().substring(0, 2).equals("AL")) {

					// allot proper runway to airline

					allotRunway = runway.getRunwayName();
					this.mapRunwayAircraft.put(aircraft, runway);
					this.availableRunways.remove(runway);

				}

				if (runway.getRunwayName().equals("Runway2") && aircraft.getFlighID().substring(0, 2).equals("PJ")) {

					// allot proper runway to private jet

					allotRunway = runway.getRunwayName();
					this.mapRunwayAircraft.put(aircraft, runway);
					this.availableRunways.remove(runway);

				}

			}

		}

		if (!this.mapRunwayAircraft.containsKey(aircraft)) {

			// there have to be a queue for some aircraft
			enqueueAircraft(aircraft);
		}

		return allotRunway;
	}

	public void enqueueAircraft(Aircraft aircraft) {

		for (Runway runway : this.registeredRunways) {

			if (runway.getRunwayName().equals("Runway1") && aircraft.getFlighID().substring(0, 2).equals("AL")) {

				// scheduled flights have to be enqueue to the Runway1

				this.mapRunwayAircraft.put(aircraft, runway);
				break;

			}

			if (runway.getRunwayName().equals("Runway2") && aircraft.getFlighID().substring(0, 2).equals("PJ")) {

				// private jet have to be enqueue to the Runway2

				this.mapRunwayAircraft.put(aircraft, runway);
				break;

			}

		}

	}

	@Override
	public String changeRunway(Aircraft aircraft) {

		int scheduledFlightEnqueued = 0;
		int privateJetsEnqueued = 0;

		String newRunway = null;

		for (Aircraft enqueuedAircraft : this.mapRunwayAircraft.keySet()) {

			if (enqueuedAircraft.getFlighID().substring(0, 2).equals("AL")
					&& this.mapRunwayAircraft.get(enqueuedAircraft).getRunwayName().equals("Runway1")) {

				// we have to count how many scheduled flights are enqueued

				scheduledFlightEnqueued++;
			}
			if (enqueuedAircraft.getFlighID().substring(0, 2).equals("PJ")
					&& this.mapRunwayAircraft.get(enqueuedAircraft).getRunwayName().equals("Runway2")) {

				// we have to count how many private jets are enqueued

				privateJetsEnqueued++;
			}

		}

		if (aircraft.getFlighID().substring(0, 2).equals("AL") && scheduledFlightEnqueued > 1
				&& privateJetsEnqueued == 0) {

			// scenario for changing runway for scheduled flights

			for (Runway runway : this.availableRunways) {
				if (runway.getRunwayName().equals("Runway2")) {
					this.mapRunwayAircraft.put(aircraft, runway);
					this.availableRunways.remove(runway);
					newRunway = runway.getRunwayName();
				}
			}

		}

		if (aircraft.getFlighID().substring(0, 2).equals("PJ") && scheduledFlightEnqueued == 0
				&& privateJetsEnqueued > 1) {

			// scenario for changing runway for private jets

			for (Runway runway : this.availableRunways) {
				if (runway.getRunwayName().equals("Runway1")) {
					this.mapRunwayAircraft.put(aircraft, runway);
					this.availableRunways.remove(runway);
					newRunway = runway.getRunwayName();
				}
			}

		}

		return newRunway;
	}

	@Override
	public String releaseRunwayOccupiedBy(Aircraft aircraft) {

		String releasedRunway = null;
		boolean alreadyAvailable = false;

		for (Runway runway : this.availableRunways) {

			if (runway.equals(this.mapRunwayAircraft.get(aircraft))) {

				alreadyAvailable = true;

			}

		}

		if (!alreadyAvailable) {
			this.availableRunways.add(this.mapRunwayAircraft.get(aircraft));
		}

		releasedRunway = this.mapRunwayAircraft.get(aircraft).getRunwayName();

		return releasedRunway;
	}

}
