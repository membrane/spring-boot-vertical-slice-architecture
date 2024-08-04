package de.predic8.features;

import de.predic8.model.Order;
import io.membrane_api.jmediator.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests only the controller implementing the API
 */
@WebMvcTest(AddOrderApi.class)
class AddOrderApiTest {

    static final UUID id = UUID.fromString("d9b8a4ea-1ec1-4d7a-8b64-bc7141319568");

    @Autowired
    MockMvc mock;

    @MockBean
    JMediator mediator;

    @BeforeEach
    void setup() throws Throwable {
        Order o = new Order();
        o.setId(id);
        when(mediator.send(new AddOrderCommand(null))).thenReturn(o);
    }

    @Test
    void whenValidInputThenReturn200() throws Exception {
        // @formatter:off
        mock.perform(post("/orders")
                .contentType(APPLICATION_JSON)
                .content("{}"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(id.toString()));
        // @formatter:on
    }

}
