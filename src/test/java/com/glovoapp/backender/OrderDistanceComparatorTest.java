package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrderDistanceComparatorTest {

	@Test
	void order1CloserThanOrder2Test() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403439, 2.150799));
		Order order1 = new Order();
		order1.withPickup(new Location(41.403439, 2.150799));
		Order order2 = new Order();
		order2.withPickup(new Location(41.403439, 2.160799));
		OrderDistanceComparator oc = new OrderDistanceComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}

	@Test
	void order2CloserThanOrder1Test() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403439, 2.150799));
		Order order1 = new Order();
		order1.withPickup(new Location(41.403439, 2.160799));
		Order order2 = new Order();
		order2.withPickup(new Location(41.403439, 2.150799));
		OrderDistanceComparator oc = new OrderDistanceComparator(courier);
        assertEquals(1, oc.compare(order1, order2));
	}
		
}
