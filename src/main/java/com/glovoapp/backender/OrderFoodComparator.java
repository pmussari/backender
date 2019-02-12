package com.glovoapp.backender;

import java.util.Comparator;

public class OrderFoodComparator implements Comparator<Order>{

	private Comparator<Order> orderByDistance;
	
	public OrderFoodComparator(Courier courier) {
		this.orderByDistance = new OrderDistanceComparator(courier);
	}
	
	@Override
	public int compare(Order order1, Order order2) {
		return order1.getFood() && !order2.getFood() ? -1 : // Difference order 2 is not food  
			(!order1.getFood() && order2.getFood() ? 1 : 	// Difference order 1 is not food 
				( order1.getFood() && order2.getFood() ? this.orderByDistance.compare(order1, order2) : // Difference by distance if both are food 
					0) ); // No difference if both are not food
	}

}
