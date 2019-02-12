package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class OrderComparatorFactoryTest {

	@Test
	void orderComparatorInstancesTest() {
		Courier courier = new Courier();
		OrderComparatorFactory ocFactory = new OrderComparatorFactory(new String[] {""},0.5);
		assertTrue( ocFactory.getComparator(OrderComparatorFactory.RANGE_CLOSER, courier) instanceof OrderDistanceRangeComparator);
		assertTrue( ocFactory.getComparator(OrderComparatorFactory.VIP, courier) instanceof OrderVIPComparator);
		assertTrue( ocFactory.getComparator(OrderComparatorFactory.FOOD, courier) instanceof OrderFoodComparator);
		assertTrue( ocFactory.getComparator(OrderComparatorFactory.DISTANCE, courier) instanceof OrderDistanceComparator);
	}

	@Test
	void orderPrioritiesTest() {
		Courier courier = new Courier();
		courier.withLocation(new Location(41.403415, 2.150776));
		OrderComparatorFactory ocFactory = new OrderComparatorFactory(new String[] {OrderComparatorFactory.RANGE_CLOSER,OrderComparatorFactory.VIP,OrderComparatorFactory.FOOD,OrderComparatorFactory.DISTANCE},0.5);
		Comparator<Order> comparator = ocFactory.getComparator(courier);
		Order order1 = new Order();
		order1.withPickup(new Location(41.405030, 2.153873));
		order1.withVip(true);
		order1.withFood(false);
		Order order2 = new Order();
		order2.withPickup(new Location(41.405532, 2.150562));
		order2.withVip(false);
		order2.withFood(true);
		Order order3 = new Order();
		order3.withPickup(new Location(41.407696, 2.157047));
		order3.withVip(false);
		order3.withFood(true);
		Order order4 = new Order();
		order4.withPickup(new Location(41.408091, 2.159280));
		order4.withVip(false);
		order4.withFood(false);
		Order order5 = new Order();
		order5.withPickup(new Location(41.410404, 2.163890));
		order5.withVip(false);
		order5.withFood(false);
		Order order6 = new Order();
		order6.withPickup(new Location(41.409522, 2.162255));
		order6.withVip(false);
		order6.withFood(false);
		List<Order> orders = new ArrayList<>();
		orders.add(order5);
		orders.add(order6);
		orders.add(order4);
		orders.add(order1);
		orders.add(order3);
		orders.add(order2);
		List<Order> prioritizedOrders = orders.stream().sorted(comparator).collect(Collectors.toList());
		//Order 1 and Order 2 are in a same range of 500m, but order 1 is prioritize by VIP client 
		assertEquals(order1, prioritizedOrders.get(0));
		assertEquals(order2, prioritizedOrders.get(1));
		//Order 3 and Order 4 are in a same range of 500m - 1000m, but order 3 is prioritize by FOOD order
		assertEquals(order3, prioritizedOrders.get(2));
		assertEquals(order4, prioritizedOrders.get(3));
		//Order 5 and Order 6 are in a same range of 1000m - 1500m, but order 6 is prioritize by distance to courier
		assertEquals(order6, prioritizedOrders.get(4));
		assertEquals(order5, prioritizedOrders.get(5));
	}
}
