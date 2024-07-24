package de.predic8.features;

import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
class GetOrdersApi {

    JMediator mediator;

    public GetOrdersApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/orders")
    List<Order> list() throws Throwable {
        return mediator.send(new GetOrdersQuery());
    }
}

record GetOrdersQuery() implements IRequest<List<Order>> { }


@Handler
class GetOrdersHandler {

    OrderRepository repo;

    public GetOrdersHandler(OrderRepository repo) {
        this.repo = repo;
    }

    List<Order> list(GetOrdersQuery ignored) {
        return repo.findAll();
    }
}