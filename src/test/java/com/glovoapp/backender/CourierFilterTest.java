package com.glovoapp.backender;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourierFilterTest {

	@Test
	void showWithGlovoBox() {
        CourierFilter courierFilter = new CourierFilter( new String[] {"pizza","cake","flamingo"} , 0); 
        Courier courier = new Courier()
                .withBox(true);
        Order orderPizza = new Order()
                .withDescription("xxxx Pizza xxx");
        Order orderCake = new Order()
                .withDescription("xxxx Cake xxx");
        Order orderFlamingo = new Order()
                .withDescription("xxxx Flamingo xxx");
        assertEquals(true, courierFilter.byGlovoBox(courier, orderPizza));
        assertEquals(true, courierFilter.byGlovoBox(courier, orderCake));
        assertEquals(true, courierFilter.byGlovoBox(courier, orderFlamingo));
	}
	
	@Test
	void hideWithoutGlovoBox() {
        CourierFilter courierFilter = new CourierFilter( new String[] {"pizza","cake","flamingo"} , 0); 
        Courier courier = new Courier()
                .withBox(false);
        Order orderPizza = new Order()
                .withDescription("xxxx Pizza xxx");
        Order orderCake = new Order()
                .withDescription("xxxx Cake xxx");
        Order orderFlamingo = new Order()
                .withDescription("xxxx Flamingo xxx");
        assertEquals(false, courierFilter.byGlovoBox(courier, orderPizza));
        assertEquals(false, courierFilter.byGlovoBox(courier, orderCake));
        assertEquals(false, courierFilter.byGlovoBox(courier, orderFlamingo));
	}

	@Test
	void showWithBicycleDistance() {
        CourierFilter courierFilter = new CourierFilter( new String[] {} , 5); 
        Courier courier = new Courier()
                .withVehicle(Vehicle.BICYCLE)
                .withLocation(new Location(41.403439, 2.150799));
        Order order = new Order()
                .withPickup(new Location(41.404180, 2.174344))
        		.withDelivery(new Location(41.403439, 2.150799));
        assertEquals(true, courierFilter.byDistance(courier, order));
	}
	@Test
	void hideWithBicycleDistance() {
        CourierFilter courierFilter = new CourierFilter( new String[] {} , 5); 
        Courier courier = new Courier()
                .withVehicle(Vehicle.BICYCLE)
                .withLocation(new Location(41.451348, 2.193494));
        Order order = new Order()
                .withPickup(new Location(41.404180, 2.174344))
        		.withDelivery(new Location(41.403439, 2.150799));
        assertEquals(false, courierFilter.byDistance(courier, order));
	}
	
	@Test
	void showWithMotorcycleDistance() {
	    CourierFilter courierFilter = new CourierFilter( new String[] {} , 5); 
        Courier courier = new Courier()
                .withVehicle(Vehicle.MOTORCYCLE)
                .withLocation(new Location(41.451348, 2.193494));
        Order order = new Order()
                .withPickup(new Location(41.404180, 2.174344))
        		.withDelivery(new Location(41.403439, 2.150799));
        assertEquals(true, courierFilter.byDistance(courier, order));
	}
	
	@Test
	void showWithElectricScooterDistance() {
	    CourierFilter courierFilter = new CourierFilter( new String[] {} , 5); 
        Courier courier = new Courier()
                .withVehicle(Vehicle.ELECTRIC_SCOOTER)
                .withLocation(new Location(41.451348, 2.193494));
        Order order = new Order()
                .withPickup(new Location(41.404180, 2.174344))
        		.withDelivery(new Location(41.403439, 2.150799));
        assertEquals(true, courierFilter.byDistance(courier, order));
	}
}
