package designmodel.mediator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLanaiAirPolicieLogic {

	private AirTrafficControllable controlTower;

	private Runway runway1;
	private Runway runway2;

	private Aircraft scheduledFlight1;
	private Aircraft scheduledFlight2;
	private Aircraft scheduledFlight3;
	private Aircraft pjet1;
	private Aircraft pjet2;
	private Aircraft pjet3;

	@Before
	public void setUp() throws Exception {

		controlTower = new LanaiAirPolicie("LANAI-airport");

		runway1 = new Runway("Runway1", 2500);
		runway2 = new Runway("Runway2", 2000);

		scheduledFlight1 = new Aircraft(controlTower, "AL-001", "Airbus480", 200);
		scheduledFlight2 = new Aircraft(controlTower, "AL-002", "Ryanair370", 150);
		scheduledFlight3 = new Aircraft(controlTower, "AL-003", "EasyJet", 130);

		pjet1 = new Aircraft(controlTower, "PJ-001", "TurboJet2.0", 20);
		pjet2 = new Aircraft(controlTower, "PJ-002", "JetDream", 18);
		pjet3 = new Aircraft(controlTower, "PJ-003", "KomodoJet", 25);

		assertNotNull(controlTower);

		assertNotNull(runway1);
		assertNotNull(runway2);

		assertNotNull(scheduledFlight1);
		assertNotNull(scheduledFlight2);
		assertNotNull(scheduledFlight3);
		assertNotNull(pjet1);
		assertNotNull(pjet2);
		assertNotNull(pjet3);

		assertEquals(true, controlTower.registerRunway(runway1));

		assertEquals("|Runway1|", controlTower.showAvailableRunway());

		assertEquals(true, controlTower.registerRunway(runway2));

		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFirstBlockAircraft() {

		// We suppose that the first block of aircrafts arrive with the following order
		// - scheduled flight_1
		// - scheduled flight_2
		// - private jet_1
		// - scheduledflight_3

		// ---------------------------------------------------------------------
		// taxiing

		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());

		assertEquals("Runway1", controlTower.allotRunwayTo(scheduledFlight1));

		assertEquals("|Runway2|", controlTower.showAvailableRunway());

		assertEquals(null, controlTower.allotRunwayTo(scheduledFlight2));

		assertEquals("Runway2", controlTower.changeRunway(scheduledFlight2));

		assertEquals("no-one available runway", controlTower.showAvailableRunway());

		assertEquals(null, controlTower.allotRunwayTo(pjet1));

		assertEquals(null, controlTower.changeRunway(pjet1));

		assertEquals(null, controlTower.allotRunwayTo(scheduledFlight3));

		assertEquals(null, controlTower.changeRunway(scheduledFlight3));

		// ---------------------------------------------------------------------

		// ---------------------------------------------------------------------
		// take off

		assertEquals("Runway1", controlTower.releaseRunwayOccupiedBy(scheduledFlight1));

		assertEquals("|Runway1|", controlTower.showAvailableRunway());

		assertEquals("Runway2", controlTower.releaseRunwayOccupiedBy(scheduledFlight2));

		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());

		assertEquals("Runway2", controlTower.releaseRunwayOccupiedBy(pjet1));

		assertEquals("Runway1", controlTower.releaseRunwayOccupiedBy(scheduledFlight3));

		// ---------------------------------------------------------------------
	}

	@Test
	public void testSecondBlockAircraft() {

		// We suppose that the second block of aircrafts arrive with the following order
		// - pjet1
		// - scheduledFlight1
		// - pjet2
		// - pjet3

		// ---------------------------------------------------------------------
		// taxiing
		assertEquals("|Runway1|Runway2|", controlTower.showAvailableRunway());

		assertEquals("Runway2", controlTower.allotRunwayTo(pjet1));

		assertEquals("|Runway1|", controlTower.showAvailableRunway());

		assertEquals("Runway1", controlTower.allotRunwayTo(scheduledFlight1));

		assertEquals("no-one available runway", controlTower.showAvailableRunway());

		assertEquals(null, controlTower.allotRunwayTo(pjet2));

		assertEquals(null, controlTower.changeRunway(pjet2));

		assertEquals(null, controlTower.allotRunwayTo(pjet3));

		assertEquals(null, controlTower.changeRunway(pjet3));

		// ---------------------------------------------------------------------

		// ---------------------------------------------------------------------
		// take off

		assertEquals("Runway2", controlTower.releaseRunwayOccupiedBy(pjet1));

		assertEquals("|Runway2|", controlTower.showAvailableRunway());

		assertEquals("Runway1", controlTower.releaseRunwayOccupiedBy(scheduledFlight1));

		assertEquals("|Runway2|Runway1|", controlTower.showAvailableRunway());

		assertEquals("Runway2", controlTower.releaseRunwayOccupiedBy(pjet2));

		assertEquals("Runway2", controlTower.releaseRunwayOccupiedBy(pjet3));

		// ---------------------------------------------------------------------
	}

}
