package clinicaodontologica.controller;

import clinicaodontologica.model.dto.OdontologoDTO;
import clinicaodontologica.utils.OdontologoFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get dentist by id successfully")
    @Order(3)
    void getById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);

    }

    @Test
    @DisplayName("Get dentist not found")
    @Order(1)
    void getByMatricula() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/v1/matricula/{matricula}", "ABC123"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No encontramos al odontologo solicitado")).andReturn();

        assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    @DisplayName("Get all dentist successfully")
    @Order(5)
    void getAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/v1/"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);

    }

    @Test
    @DisplayName("Create dentist successfully")
    @Order(2)
    void newDentist() throws Exception {
        OdontologoDTO dto = OdontologoFactory.createOdontologoDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(payloadJson, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Update dentist successfully")
    @Order(4)
    void modifyDentist() throws Exception {
        OdontologoDTO dto = OdontologoFactory.createOdontologoDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/v1/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
        assertEquals(payloadJson, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Delete dentist by id successfully")
    @Order(6)
    void deleteDentist() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
    }
}