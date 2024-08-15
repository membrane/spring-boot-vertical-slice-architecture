package de.predic8.features;

import de.predic8.exceptions.*;
import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static de.predic8.model.Order.State.*;

@RestController
class CancelOrderApi {

    JMediator mediator;

    public CancelOrderApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @PutMapping ("/orders/{id}/cancel")
    void cancel(@PathVariable UUID id) throws Throwable {
        mediator.send(new CancelOrderCommand(id));
    }
}

record CancelOrderCommand(UUID id) implements IRequest<Void> { }

@Handler
class CancelHandler {

    OrderRepository repo;

    public CancelHandler(OrderRepository repo) {
        this.repo = repo;
    }

    void cancel(CancelOrderCommand command) {
        Order order = repo.findById(command.id()).orElseThrow();

        if (order.getState() != ORDERED)
            throw new WrongStateException();

        order.setState(CANCELED);
        repo.save(order);
    }
}