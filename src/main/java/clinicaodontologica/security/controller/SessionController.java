package clinicaodontologica.security.controller;

import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.LoginResponseDTO;
import clinicaodontologica.security.dto.SignupDTO;
import clinicaodontologica.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("session/v1/")
public class SessionController {

    private UserService userService;


    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        String token = userService.login(loginRequestDTO);
        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignupDTO signupDTO) {
        userService.signUp(signupDTO);
        return new ResponseEntity<>("El usuario se creo correctamente", HttpStatus.CREATED);
    }
}
