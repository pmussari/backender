package com.glovoapp.backender;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderComparatorFactory {

	String[] priorities;
	
	public static final String RANGE_CLOSER = "range_closer";
	public static final String VIP = "vip";
	public static final String FOOD = "food";
	public static final String DISTANCE = "distance";
	
	public OrderComparatorFactory(@Value("${backender.sortPriorities}") String[] priorities) {
		this.priorities = priorities;
	}
	
	public Comparator<Order> getComparator(Courier courier) {
		Comparator<Order> comparator = null ,comparatorNext = null;
		for (String type : priorities) {
			comparatorNext  = this.getComparator(type, courier);
			if(comparator == null && comparatorNext != null) {
				comparator = comparatorNext;
			}else if(comparatorNext != null) {
				comparator = comparator.thenComparing(comparatorNext);
			}
		}
		return comparator;
	}

	public Comparator<Order> getComparator(String type,Courier courier) {
		if(OrderComparatorFactory.RANGE_CLOSER.equals(type)) {
			return new OrderDistanceRangeComparator(courier);
		}else if(OrderComparatorFactory.VIP.equals(type)) {
			return new OrderVIPComparator(courier);
		}else if(OrderComparatorFactory.FOOD.equals(type)) {
			return new OrderFoodComparator(courier);
		}else if(OrderComparatorFactory.DISTANCE.equals(type)) {
			return new OrderDistanceComparator(courier);
		}else {
			return null;
		}
	}

}
