package de.predic8.features;

import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.web.servlet.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GetOrdersTest {

    @Autowired
    MockMvc mock;
    
    @Autowired
    ObjectMapper om;

    @Test
    void getOrders() throws Exception {

        ResultActions r = mock.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));

        JsonNode n = om.readTree(r.andReturn().getResponse().getContentAsString());

        assertEquals(2,n.size());

        JsonNode order1 = n.get(0);

        assertDoesNotThrow(() -> {
            UUID.fromString(order1.get("id").asText());
        });
    }
}
