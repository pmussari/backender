package com.glovoapp.backender;

import java.util.Comparator;

public class OrderDistanceRangeComparator implements Comparator<Order>{

	private Courier courier;
	private final double rangeKm = 0.5;
	
	public OrderDistanceRangeComparator(Courier courier) {
		this.courier = courier;
	}
	
	
	@Override
	public int compare(Order order1, Order order2) {
		return this.getRange(courier.getLocation(),order1.getPickup()).compareTo(this.getRange(courier.getLocation(),order2.getPickup()));
	}
	
	private Double getRange(Location courierPosition,Location orderPickup) {
		return new Double(Math.floor(DistanceCalculator.calculateDistance(courierPosition, orderPickup) / rangeKm));
	}

}
