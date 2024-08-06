package de.predic8.features;

import de.predic8.exceptions.*;
import de.predic8.model.Order;
import de.predic8.model.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.util.*;

import static de.predic8.model.Order.State.*;
import static java.util.UUID.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests only a handler without database, mediator and API
 */
class CancelOrderHandlerTest {

    public static final UUID createdId = randomUUID();
    public static final UUID orderedId = randomUUID();

    CancelHandler handler;

    @BeforeEach
    void setup() {
        handler = new CancelHandler(getOrderRepositoryMock());
    }

    @Test
    void wrongStateForCancel() {
        assertThrows(  WrongStateException.class, () -> handler.cancel(new CancelOrderCommand(createdId)));
    }

    @Test
    void rightStateForCancel() {
        assertDoesNotThrow(  () -> handler.cancel(new CancelOrderCommand(orderedId)));
    }

    private static OrderRepository getOrderRepositoryMock() {
        OrderRepository repo = mock(OrderRepository.class);
        when(repo.findById(createdId)).thenReturn(Optional.of(getCreated()));
        when(repo.findById(orderedId)).thenReturn(Optional.of(getOrdered()));
        return repo;
    }

    private static Order getCreated() {
        return new Order(List.of(new Item("Lolly",5, BigDecimal.valueOf(2.98))));
    }

    private static Order getOrdered() {
        Order ordered = new Order(List.of(new Item("Jelly",1, BigDecimal.valueOf(0.79))));
        ordered.setState(ORDERED);
        return ordered;
    }
}