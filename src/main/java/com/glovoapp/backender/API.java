package com.glovoapp.backender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@ComponentScan("com.glovoapp.backender")
@EnableAutoConfiguration
class API {
    private final String welcomeMessage;
    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;
    private final CourierFilter courierFilter;
    private final OrderComparatorFactory orderComparatorFactory;

    @Autowired
    API(@Value("${backender.welcome_message}") String welcomeMessage, OrderRepository orderRepository,CourierRepository courierRepository,CourierFilter courierFilter,OrderComparatorFactory orderComparatorFactory) {
        this.welcomeMessage = welcomeMessage;
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
        this.courierFilter = courierFilter;
        this.orderComparatorFactory  = orderComparatorFactory;
    }

    @RequestMapping("/")
    @ResponseBody
    String root() {
        return welcomeMessage;
    }

    @RequestMapping("/orders")
    @ResponseBody
    List<OrderVM> orders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderVM(order.getId(), order.getDescription()))
                .collect(Collectors.toList());
    }

    @RequestMapping("/orders/{courierId}")
    @ResponseBody
    List<OrderVM> courierOrders(@PathVariable("courierId") String courierId) {
    	Courier courier = this.courierRepository.findById(courierId);
        return orderRepository.findAll()
                .stream()
                .filter(order -> courierFilter.check(courier, order))
                .sorted( orderComparatorFactory.getComparator(courier) )
                .map(order -> new OrderVM(order.getId(), order.getDescription()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        SpringApplication.run(API.class);
    }
}
