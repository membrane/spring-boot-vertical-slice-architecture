package de.predic8.features;

import de.predic8.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;

import java.math.*;
import java.util.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = AddOrderApi.class)
@AutoConfigureMockMvc
class ApiTest {

    @Autowired
    AddOrderApi api;

    @Test
    void add() throws Throwable {
        api.add(new AddOrderCommand(List.of(new Item("Dauerlutscher",5,new BigDecimal("1.0")))));
    }

}

