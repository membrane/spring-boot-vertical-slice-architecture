package de.predic8.features;

import de.predic8.model.Order;
import de.predic8.model.*;
import org.junit.jupiter.api.*;

import java.math.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests only a handler without database, mediator and API
 */
class GetOrderHandlerTest {

    public static final UUID id = UUID.fromString("65ab3d72-51c1-4e9f-91ae-e7a710e481e4");

    GetOrderHandler handler;

    OrderRepository repo;

    Order order;

    @BeforeEach
    void setup() {
        order = new Order(List.of(new Item("Lolly",5, BigDecimal.valueOf(2.98))));

        repo = mock(OrderRepository.class);
        when(repo.findById(id)).thenReturn(Optional.of(order));

        handler = new GetOrderHandler(repo);
    }

    @Test
    void getOrderById() {
        assertEquals("Lolly", handler.get(new GetOrderQuery(id)).getItems().get(0).getArticle());
    }
}