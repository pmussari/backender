package com.glovoapp.backender;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CourierFilter {
	
	private final String[] glovoBoxKeys;
	
	private final int distanceLimitForBicycle;

	public CourierFilter(@Value("${backender.glovoBoxKeys}") String[] glovoBoxKeys,@Value("${backender.distanceLimitForBicycle}") int distanceLimitForBicycle) {
		this.glovoBoxKeys = glovoBoxKeys;
		this.distanceLimitForBicycle = distanceLimitForBicycle;
	}
	
	public boolean byGlovoBox(Courier courier,Order order) {
		return 
		!Arrays.stream(glovoBoxKeys).parallel().anyMatch(order.getDescription().toLowerCase()::contains)
		|| courier.getBox();
	}
	
	public boolean byDistance(Courier courier,Order order) {
		return DistanceCalculator.calculateDistance(courier.getLocation(),order.getPickup()) < distanceLimitForBicycle 
				|| Vehicle.ELECTRIC_SCOOTER.equals(courier.getVehicle()) 
				|| Vehicle.MOTORCYCLE.equals(courier.getVehicle()); 
	}

	public boolean check(Courier courier,Order order) {
		return this.byGlovoBox(courier, order) && this.byDistance(courier, order);
	}
}
