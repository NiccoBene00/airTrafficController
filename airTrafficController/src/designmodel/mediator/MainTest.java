package designmodel.mediator;

public class MainTest {

	public static void main(String[] args) {

		AirTrafficControllable controlTower = new LanaiAirPolicie("LANAI-airport"); // mediator

		Runway runway1 = new Runway("Runway1", 2500);
		Runway runway2 = new Runway("Runway2", 2000);

		controlTower.registerRunway(runway1);
		controlTower.registerRunway(runway2);

		Aircraft airline1 = new Aircraft(controlTower, "AL-001", "Airbus480", 200);
		Aircraft airline2 = new Aircraft(controlTower, "AL-002", "Ryanair370", 150);
		Aircraft airline3 = new Aircraft(controlTower, "AL-003", "EasyJet", 130);
		Aircraft pjet1 = new Aircraft(controlTower, "PJ-001", "TurboJet2.0", 20);

		airline1.taxxing();
		airline2.taxxing();
		pjet1.taxxing();
		airline3.taxxing();

		airline1.takeoff();
		airline2.takeoff();
		pjet1.takeoff();
		airline3.takeoff();

	}

}
