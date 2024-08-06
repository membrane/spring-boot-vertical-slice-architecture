package de.predic8.config;

import de.predic8.model.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Component
public class DataLoader implements ApplicationRunner {

    private final OrderRepository repo;

    public DataLoader(OrderRepository repo) {
        this.repo = repo;
    }

    public void run(ApplicationArguments args) {
        Order jelly = new Order(List.of(new Item("Jelly", 10, BigDecimal.valueOf(0.78))));
        jelly.setId(UUID.fromString("8ca418ff-03f1-4fa7-8968-792d6c682a6e"));
        repo.save(jelly);

        Order chocolate = new Order(List.of(new Item("Chocolate", 2, BigDecimal.valueOf(1.35))));
        chocolate.setId(UUID.fromString("2994d31e-4f94-43fe-ac58-fd65586cc449"));
        chocolate.setState(Order.State.ORDERED);
        repo.save(chocolate);
    }
}