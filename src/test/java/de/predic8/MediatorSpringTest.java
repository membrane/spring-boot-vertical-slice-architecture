package de.predic8;

import io.membrane_api.jmediator.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MediatorSpringTest {

    @Autowired
    JMediator mediator;
    
    @Test
    void foo() throws Throwable {
        assertEquals("fooBaz",mediator.send(new FooRequest("Baz")));
    }

}

record FooRequest(String name) implements IRequest<String> {}

@Handler
class FooHandler {
    String foo(FooRequest s) {
        return "foo" + s.name();
    }
}

