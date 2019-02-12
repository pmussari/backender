package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderDistanceRangeComparatorTest {

	@Test
	void order1CloserRangeThanOrder2Test() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403439, 2.150799));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405986, 2.150078));
		Order order2 = new Order();
		order2.withPickup(new Location(41.406414, 2.156512));
		OrderDistanceRangeComparator oc = new OrderDistanceRangeComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}
	
	@Test
	void order2CloserRangeThanOrder1Test() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403439, 2.150799));
		Order order1 = new Order();
		order1.withPickup(new Location(41.406414, 2.156512));
		Order order2 = new Order();
		order2.withPickup(new Location(41.405986, 2.150078));
		OrderDistanceRangeComparator oc = new OrderDistanceRangeComparator(courier);
		assertEquals(1, oc.compare(order1, order2));
	}
	
	@Test
	void order1SameRangeThanOrder2Test() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405030, 2.153873));
		Order order2 = new Order();
		order2.withPickup(new Location(41.405532, 2.150562));
		OrderDistanceRangeComparator oc = new OrderDistanceRangeComparator(courier);
		assertEquals(0, oc.compare(order1, order2));
	}
}
