package clinicaodontologica.controller;

import clinicaodontologica.model.dto.PacienteDTO;
import clinicaodontologica.utils.PacienteFactory;
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
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get patient by id successfully")
    @Order(3)
    void getById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);

    }

    @Test
    @DisplayName("Get patient not found")
    @Order(1)
    void getByNameOrSurname() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/v1/nombre/{name}", "Maria"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No encontramos al paciente solicitado")).andReturn();

        assertEquals(result.getResponse().getStatus(), 404);
    }

    @Test
    @DisplayName("Get all patients successfully")
    @Order(5)
    void getAll() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/v1/"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    @DisplayName("Create patient successfully")
    @Order(2)
    void newPatient() throws Exception {

        PacienteDTO dto = PacienteFactory.createPacienteDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(payloadJson, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Update patient NOT FOUND")
    @Order(4)
    void modifyPatient() throws Exception {
        PacienteDTO dto = PacienteFactory.createPacienteDTO();
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/pacientes/v1/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
        assertEquals(payloadJson, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Delete patient by id NOT FOUND")
    @Order(6)
    void deletePatient() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
    }

}