package designmodel.mediator;

public interface AirTrafficControllable {

	public boolean registerRunway(Runway runway);

	public boolean unregisterRunway(Runway runway);

	public String showAvailableRunway();

	public String allotRunwayTo(Aircraft aircraft);

	public String changeRunway(Aircraft aircraft);

	public String releaseRunwayOccupiedBy(Aircraft aircraft);

}
