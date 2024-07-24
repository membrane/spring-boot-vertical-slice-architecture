package de.predic8.features;

import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

import static org.springframework.http.HttpStatus.*;

@RestController
class GetOrderApi {

    JMediator mediator;

    public GetOrderApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/orders/{id}")
    Order get(@PathVariable("id") UUID id) throws Throwable {
        return mediator.send(new GetOrderQuery(id));
    }
}

record GetOrderQuery(UUID id) implements IRequest<Order> { }

@Handler
class GetOrderHandler {

    OrderRepository repo;

    public GetOrderHandler(OrderRepository repo) {
        this.repo = repo;
    }

    Order get(GetOrderQuery query) {
        return repo.findById(query.id()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }
}