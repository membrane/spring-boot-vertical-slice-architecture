package de.predic8.features;

import de.predic8.exceptions.*;
import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
class AddOrderApi {

    JMediator mediator;

    public AddOrderApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/orders")
    @ResponseStatus(CREATED)
    Order add(@RequestBody AddOrderCommand command) throws Throwable {
        return mediator.send(command);
    }
}

record AddOrderCommand(List<Item> items) implements IRequest<Order> { }

@Validator
class AddValidator {
    void validate(AddOrderCommand command) {
        command.items().forEach(item -> {
            if(item.getQuantity() > 100) {
                throw new TooMuchException();
            }
        });
    }
}

@Handler
class AddHandler {

    OrderRepository repo;

    public AddHandler(OrderRepository repo) {
        this.repo = repo;
    }

    Order add(AddOrderCommand command) {
        Order o = new Order(command.items());
        o.setId(UUID.randomUUID());
        return repo.save(o);
    }
}
