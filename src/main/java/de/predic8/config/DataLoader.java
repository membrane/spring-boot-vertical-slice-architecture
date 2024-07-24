package de.predic8.config;

import de.predic8.exceptions.*;
import de.predic8.model.*;
import org.springframework.boot.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.math.*;
import java.net.*;
import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class DataLoader implements ApplicationRunner {

    private OrderRepository repo;

    public DataLoader(OrderRepository repo) {
        this.repo = repo;
    }

    public void run(ApplicationArguments args) {
        var o1 = new Order();
        o1.setItems(List.of(new Item("Jelly",10,BigDecimal.valueOf(0.78))));
        repo.save(o1);

        var o2 = new Order();
        o2.setItems(List.of(new Item("Chocolate",2,BigDecimal.valueOf(1.35))));
        repo.save(o2);
    }

}