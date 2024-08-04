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
class AddOrderTest {

    @Autowired
    MockMvc mock;
    
    @Autowired
    ObjectMapper om;

    @Test
    void addOrder() throws Exception {

        ResultActions r = mock.perform(post("/orders")
                        .contentType(APPLICATION_JSON)
                        .content("""
                            {
                              "items": [
                                {
                                  "quantity": 5,
                                  "article": "Dauerlutscher",
                                  "price": 3.7
                                }
                              ]
                            }"""))
                .andDo(print())
                .andExpect(status().isCreated());
        
        String location = r.andReturn().getResponse().getHeader("Location");

        System.out.println("location = " + location);

        JsonNode n = om.readTree(r.andReturn().getResponse().getContentAsString());

        assertEquals(2,n.size());

        JsonNode order1 = n.get(0);

        assertDoesNotThrow(() -> {
            UUID.fromString(order1.get("id").asText());
        });
    }
}
