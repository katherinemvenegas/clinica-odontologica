package clinicaodontologica.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get turn not found")
    @Order(1)
    void getByDate() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/v1/fecha").param("d", "31/12/2022"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No encontramos turnos en la fecha indicada")).andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }
}