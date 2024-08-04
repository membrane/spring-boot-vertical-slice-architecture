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
    void add(@PathVariable UUID id, @RequestBody Order order) throws Throwable {
        mediator.send(new UpdateOrderCommand(id,order));
    }
}

record UpdateOrderCommand(UUID id, Order items) implements IRequest<Void> { }


@Handler
class UpdateHandler {

    OrderRepository repo;

    public UpdateHandler(OrderRepository repo) {
        this.repo = repo;
    }

    UUID update(UpdateOrderCommand command) {

        Order order = repo.getReferenceById(command.id());
        order.setItems(command.items().getItems());
        return repo.save(order).getId();
    }
}
