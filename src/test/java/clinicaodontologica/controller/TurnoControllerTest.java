package clinicaodontologica.controller;

import clinicaodontologica.model.dto.TurnoDTO;
import clinicaodontologica.persistence.entities.Odontologo;
import clinicaodontologica.persistence.entities.Paciente;
import clinicaodontologica.persistence.repository.IOdontologoRepository;
import clinicaodontologica.persistence.repository.IPacienteRepository;
import clinicaodontologica.utils.OdontologoFactory;
import clinicaodontologica.utils.PacienteFactory;
import clinicaodontologica.utils.TurnoFactory;
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
class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IOdontologoRepository iOdontologoRepository;

    @Autowired
    private IPacienteRepository iPacienteRepository;

    @BeforeEach
    void setUp() {

        Odontologo odontologo = OdontologoFactory.getDentist();
        iOdontologoRepository.save(odontologo);

        Paciente paciente = PacienteFactory.getPatient();
        iPacienteRepository.save(paciente);


    }

    @Test
    @DisplayName("Get turn by id successfully")
    @Order(3)
    void getById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    @DisplayName("Get all turns successfully")
    @Order(4)
    void getAll() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/v1/"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

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

    @Test
    @DisplayName("Create turn successfully")
    @Order(2)
    void newTurn() throws Exception {

        TurnoDTO dto = TurnoFactory.createTurnoDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/turnos/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        assertEquals(payloadJson, result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Update turn successfully")
    @Order(5)
    void modifyTurn() throws Exception {

        TurnoDTO dto = TurnoFactory.createTurnoDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/turnos/v1/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andReturn();

        assertEquals(409, result.getResponse().getStatus());

    }

    @Test
    @DisplayName("Delete turn successfully")
    @Order(6)
    void deleteTurn() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/v1/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}