package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderFoodComparatorTest {

	@Test
	void order1FoodTest() {
		Courier courier = new Courier();
		Order order1 = new Order();
		order1.withFood(true);
		Order order2 = new Order();
		order2.withFood(false);
		OrderFoodComparator oc = new OrderFoodComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}
	
	@Test
	void order2FoodTest() {
		Courier courier = new Courier();
		Order order1 = new Order();
		order1.withFood(false);
		Order order2 = new Order();
		order2.withFood(true);
		OrderFoodComparator oc = new OrderFoodComparator(courier);
		assertEquals(1, oc.compare(order1, order2));
	}
	
	@Test
	void order1FoodCloserThanOrder2FoodTest() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405532, 2.150562));
		order1.withFood(true);
		Order order2 = new Order();
		order2.withPickup(new Location(41.405030, 2.153873));
		order2.withFood(true);
		OrderFoodComparator oc = new OrderFoodComparator(courier);
		assertEquals(-1, oc.compare(order1, order2));
	}
	
	@Test
	void order2FoodCloserThanOrder1FoodTest() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		Order order1 = new Order();
		order1.withPickup(new Location(41.405030, 2.153873));
		order1.withFood(true);
		Order order2 = new Order();
		order2.withPickup(new Location(41.405532, 2.150562));
		order2.withFood(true);
		OrderFoodComparator oc = new OrderFoodComparator(courier);
		assertEquals(1, oc.compare(order1, order2));
	}
}
