package com.glovoapp.backender;

import java.util.Comparator;

public class OrderVIPComparator implements Comparator<Order>{

	private Comparator<Order> orderByDistance;
	
	public OrderVIPComparator(Courier courier) {
		this.orderByDistance = new OrderDistanceComparator(courier);
	}
	@Override
	public int compare(Order order1, Order order2) {
		return order1.getVip() && !order2.getVip() ? -1 : // Difference order 2 is not vip
			(!order1.getVip() && order2.getVip() ? 1 : // Difference order 1 is not vip
				( order1.getVip() && order2.getVip() ? this.orderByDistance.compare(order1, order2) : // Difference by distance if both are vip
					0 ) ); // No difference if both are not vip
	}

}
