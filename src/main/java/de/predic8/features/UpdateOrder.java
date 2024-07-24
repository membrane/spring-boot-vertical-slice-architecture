package de.predic8.features;

import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
class UpdateOrderApi {

    JMediator mediator;

    public UpdateOrderApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @PutMapping ("/orders/{id}")
    UUID add(@PathVariable UUID id, @RequestBody Order order) throws Throwable {
        return mediator.send(new UpdateOrderCommand(id,order));
    }
}

record UpdateOrderCommand(UUID id, Order items) implements IRequest<UUID> { }


@Handler
class UpdateHandler {

    OrderRepository repo;

    public UpdateHandler(OrderRepository repo) {
        this.repo = repo;
    }

    UUID add(AddOrderCommand command) {

        Order order = new Order();
        order.setItems(command.items());

        return repo.save(order).getId();
    }
}
