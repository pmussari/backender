package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderVipComparatorTest{

	@Test
	void order1VipTest() {
		Courier courier = new Courier();
		Order order1 = new Order();
		order1.withVip(true);
		Order order2 = new Order();
		order2.withVip(false);
		OrderVIPComparator oc = new OrderVIPComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}
	
	@Test
	void order2VipTest() {
		Courier courier = new Courier();
		Order order1 = new Order();
		order1.withVip(false);
		Order order2 = new Order();
		order2.withVip(true);
		OrderVIPComparator oc = new OrderVIPComparator(courier);
		assertEquals(1, oc.compare(order1, order2));
	}
	
	@Test
	void order1VipCloserThanOrder2VipTest() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405532, 2.150562));
		order1.withVip(true);
		Order order2 = new Order();
		order2.withPickup(new Location(41.405030, 2.153873));
		order2.withVip(true);
		OrderVIPComparator oc = new OrderVIPComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}
	
	@Test
	void order2VipCloserThanOrder1VipTest() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405030, 2.153873));
		order1.withVip(true);
		Order order2 = new Order();
		order2.withPickup(new Location(41.405532, 2.150562));
		order2.withVip(true);
		OrderVIPComparator oc = new OrderVIPComparator(courier);
		assertEquals(1, oc.compare(order1, order2));
	}
}
