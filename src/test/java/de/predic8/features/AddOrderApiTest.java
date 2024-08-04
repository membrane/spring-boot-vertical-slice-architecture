package de.predic8.features;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.test.web.servlet.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(AddOrderApi.class)
class AddOrderApiTest {

    @Autowired
    private MockMvc mock;

    @Test
    void whenValidInput_thenReturns200() throws Exception {

        mock.perform(post("/orders"));
    }

}
