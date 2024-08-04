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
        repo.save(new Order(List.of(new Item("Jelly",10,BigDecimal.valueOf(0.78)))));
        repo.save(new Order(List.of(new Item("Chocolate",2,BigDecimal.valueOf(1.35)))));
    }
}