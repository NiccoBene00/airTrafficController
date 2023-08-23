package designmodel.mediator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestManagementRunway {

	private AirTrafficControllable controlTower;
	private Runway runway1;
	private Runway runway2;
	private Runway runway3;

	@Before
	public void setUp() throws Exception {

		controlTower = new LanaiAirPolicie("LANAI-airport");

		runway1 = new Runway("Runway1", 2500);
		runway2 = new Runway("Runway2", 2000);
		runway3 = new Runway("Runway3", 3000);

		assertNotNull(controlTower);
		assertNotNull(runway1);
		assertNotNull(runway2);
		assertNotNull(runway3);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterUnregisterRunway() {

		assertEquals(true, controlTower.registerRunway(runway1));

		assertEquals("|Runway1|", controlTower.showAvailableRunway());

		assertEquals(true, controlTower.registerRunway(runway2));

		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());

		assertEquals(true, controlTower.registerRunway(runway3));

		assertEquals("|Runway1|Runway2|Runway3|", controlTower.showAvailableRunway());

		assertEquals(true, controlTower.unregisterRunway(runway3));

		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());
	}

}
