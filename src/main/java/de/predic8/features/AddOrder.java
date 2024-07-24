package de.predic8.features;

import de.predic8.exceptions.*;
import de.predic8.model.*;
import io.membrane_api.jmediator.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
class AddOrderApi {

    JMediator mediator;

    public AddOrderApi(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/orders")
    UUID add(@RequestBody AddOrderCommand command) throws Throwable {
        return mediator.send(command);
    }
}

record AddOrderCommand(List<Item> items) implements IRequest<UUID> { }

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

    UUID add(AddOrderCommand command) {
        return repo.save(new Order() {{
            setItems(command.items());
        }}).getId();
    }
}
