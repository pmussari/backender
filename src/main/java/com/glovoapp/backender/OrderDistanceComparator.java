package com.glovoapp.backender;

import java.util.Comparator;

public class OrderDistanceComparator implements Comparator<Order>{

	private Courier courier;
	
	public OrderDistanceComparator(Courier courier) {
		this.courier = courier;
	}
	
	
	@Override
	public int compare(Order order1, Order order2) {
		return new Double(DistanceCalculator.calculateDistance(courier.getLocation(), order1.getPickup()))
				.compareTo(new Double(DistanceCalculator.calculateDistance(courier.getLocation(), order2.getPickup())));
	}

}
