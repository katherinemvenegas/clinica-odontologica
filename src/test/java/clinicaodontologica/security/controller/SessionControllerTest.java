package clinicaodontologica.security.controller;

import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.SignupDTO;
import clinicaodontologica.utils.UsuarioFactory;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Login successfully")
    @Order(2)
    void loginUser() throws Exception {
        LoginRequestDTO dto = UsuarioFactory.createRequest();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    @DisplayName("New user successfully")
    @Order(1)
    void signUp() throws Exception {

        SignupDTO dto = UsuarioFactory.createSignUpDTO();

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(dto);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/session/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());
    }
}